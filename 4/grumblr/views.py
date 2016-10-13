from django.shortcuts import render, redirect
from django.core.exceptions import ObjectDoesNotExist
from django.contrib.auth.decorators import login_required
from django.core.mail import send_mail
from django.contrib.auth.models import User
from django.contrib.auth.tokens import default_token_generator
from grumblr.models import *
from grumblr.forms import *
from django.http import HttpResponse, Http404
from mimetypes import guess_type
from django.urls import reverse

#manually
from django.contrib.auth import login, authenticate

# Create your views here.

def login_page(request):
    context = {}
    errors = []

    if request.method == 'GET':
        context['form'] = LoginForm()
        return render(request, 'grumblr/login.html',context)

    form = LoginForm(request.POST)
    context['form'] = form

    # Validates the form.
    if not form.is_valid():
        return render(request, 'grumblr/index.html', context)
    
    user = authenticate(username =request.POST['username'], password=form.cleaned_data['password1'])
    if user is None:
        errors.append('Login Failed')
        context['errors'] = errors
        return render(request, 'grumblr/login.html', context)

    login(request, user)
    return redirect('post')

def reg(request):

    context = {}

    # to display the same page on a get request 
    if request.method == 'GET':
        context['form'] = RegistrationForm()
        return render(request, 'grumblr/index.html', context)


    # Creates a bound form from the request POST parameters and makes the 
    # form available in the request context dictionary.
    form = RegistrationForm(request.POST)
    context['form'] = form


    # Validates the form.
    if not form.is_valid():
        return render(request, 'grumblr/index.html', context)
    
    # If we get here the form data was valid.  Register and login the user.
    new_user = User.objects.create_user(username=request.POST['username'], 
                                        password=form.cleaned_data['password1'], first_name = form.cleaned_data['first_name'], last_name = form.cleaned_data['last_name'], email = form.cleaned_data['email_id'])
    
    new_user.save()
    nu1 = User.objects.get(username = request.POST['username'])
    username = nu1.username
    email_identification = nu1.email
    nu1.is_active = False
    nu1.save()
        
    token = default_token_generator.make_token(nu1)
    body = """ Please click on the link below to confirm password
    http://%s%s"""%(request.get_host(), reverse('emailconfirmation', args = (username, token)))

    send_mail(
    subject = "change password",
    message = body,
    from_email = "samyakmehta@gmail.com",
    recipient_list = [nu1.email]
    )
    
    # Logs in the new user and redirects to his/her todo list
    new_user = authenticate(username=request.POST['username'], 
                            password=form.cleaned_data['password1'])
    if new_user is not None:
        login(request,new_user)
    
    nu = User.objects.get(username = request.POST['username'])
    new_profile_value = Profile(user_name = nu)
    new_profile_value.token = token
    new_profile_value.save()
    return render(request, 'grumblr/index.html', context)

@login_required
def post(request):

    context = {}
    errors = []
    
    all_items = Item.objects.all()
    
    if request.method=='GET':
        form = PostForm()
        context ={
        'queryset': all_items, 'form' : form
        }
        return render(request, 'grumblr/post.html', context)
    
    form = PostForm(request.POST)
    if not form.is_valid(): 
        return render(request, 'grumblr/post.html', context)

    new_post = Item(post_text = form.cleaned_data['postFeed'], uname = request.user) 
    new_post.save()
    all_items = Item.objects.all()
    context ={
    'queryset': all_items,
    'form' : form,
    'errors': errors
    }
    return render(request, 'grumblr/post.html', context)

@login_required
def profile(request):
    followUnfollowButton = ''

    if request.method=='GET':
        u = request.user
    else:
        if 'follow' in request.POST:
            u = User.objects.get(username = request.POST['follow'])
        elif 'unfollow' in request.POST:
            u = User.objects.get(username = request.POST['unfollow'])
        else:           
            u = User.objects.get(username = request.POST['req'])        
    info = User.objects.get(username = u)
    agebio = Profile.objects.get(user_name = info)    
    profileinfo = Item.objects.filter(uname = info)


    currentUser = Profile.objects.get(user_name = request.user)
    userfollowed = currentUser.follower.all()

    if info in userfollowed:
        followUnfollowButton = 'unfollow'

    if 'follow' in request.POST:
        followeduser = User.objects.get(username = request.POST['follow'])
        originaluser = User.objects.get(username = request.user)
        bioorginalUser = Profile.objects.get(user_name = request.user)
        bioorginalUser.follower.add(followeduser)
        bioorginalUser.save();

    if 'unfollow' in request.POST:
        followeduser = User.objects.get(username = request.POST['unfollow'])
        originaluser = User.objects.get(username = request.user)
        bioorginalUser = Profile.objects.get(user_name = request.user)
        bioorginalUser.follower.remove(followeduser)
        bioorginalUser.save();



    context ={'profileinfo': profileinfo, 'userinfo': info, 'agebio': agebio, 'followUnfollowButton': followUnfollowButton}
    return render(request, 'grumblr/profile.html', context)


@login_required
def retrieve_image(request, username):
    user = User.objects.get(username = username)
    fullInfoFromProfile = Profile.objects.get(user_name = user)
    if not fullInfoFromProfile:
        raise Http404
    content_type = guess_type(fullInfoFromProfile.image.name)
    return HttpResponse(fullInfoFromProfile.image)

@login_required
def editprofile(request):
    context = {}

    if request.method=='GET':
        form = EditProfileForm()
        form1 = ImageForm()
        if not Profile.objects.filter(user_name = request.user):
            Profile(user_name = request.user).save()
        context['form'] = form
        context['form1'] = form1
        return render(request, 'grumblr/editprofile.html', context)

    if 'editUserData' in request.POST: 
        form = EditProfileForm(request.POST)
        
        # Validates the form.
        if not form.is_valid():
            return render(request, 'grumblr/editprofile.html', context)
        
        # If we get here the form data was valid.  Register and login the user.
        np = Profile.objects.get(user_name = request.user)
        np.age = form.cleaned_data['age']
        np.bio = form.cleaned_data['bio']
        np.save()

        userchanged = User.objects.get(username = request.user)
        userchanged.first_name = form.cleaned_data['first_name']
        userchanged.last_name = form.cleaned_data['last_name']
        userchanged.set_password(form.cleaned_data['password1'])
        form1 = ImageForm(Profile.objects.get(user_name = request.user).image)
        userchanged.save()
        login(request,userchanged)
    else:
        if request.method == 'POST':
            profileinformation = Profile.objects.get(user_name = request.user)
            userinformation = User.objects.get(username = request.user)
            form = EditProfileForm(request.POST)
            form1= ImageForm(request.POST, request.FILES)
            if form1.is_valid():
                m = Profile.objects.get(user_name = request.user) 
                m.image = form1.cleaned_data['imageFile']
                m.save()
        
    context['form'] = form
    context['form1'] = form1    
    return render(request, 'grumblr/editprofile.html', context)


def displayfollow(request):
    context ={}
    currentUser = Profile.objects.get(user_name = request.user)
    userfollowed = currentUser.follower.all()    
    items =[]
    for user in userfollowed:
        items += Item.objects.filter(uname = user)

    items.sort(key = lambda x: x.datetime, reverse=True)        
    context['queryset'] = items
    return render(request, 'grumblr/follower.html', context)



def forgotPassword(request):
    context ={}
    if request.method == 'GET':
        context['form'] = ForgotPasswordForm()
        return render(request, 'grumblr/forgotpassword.html', context)

    form = ForgotPasswordForm(request.POST)
    context['form'] = form


    # Validates the form.
    if not form.is_valid():
        return render(request, 'grumblr/forgotpassword.html', context)
     
    usernameFPassword = request.POST['username']
    emailAddressFPassword = request.POST['email_id']   

    user = User.objects.get(username = request.POST['username'])
    token = default_token_generator.make_token(user)
    Profile.objects.filter(user_name = user).update(token = token)
    body = """ Please click on the link below to reset password
    http://%s%s"""%(request.get_host(), reverse('confirm', args = (usernameFPassword, token)))

    send_mail(
    subject = "change password",
    message = body,
    from_email = "samyakmehta@gmail.com",
    recipient_list = [user.email]
    )
    context['message'] = 'Reset password link sent to the regiserted email'
    context['form'] = form
    return render(request, 'grumblr/forgotpassword.html')

def changepassword(request):
    context = {}
    if request.method == 'GET':
        return redirect('post')
    username = request.POST['changepasswordbutton']
    owner = User.objects.get(username = username)
    owner.set_password(request.POST['password1'])
    owner.save()
    login(request, owner)
    return redirect(post)

def renderChangePasswordPage(request, username, token):
    context = {}
    user = User.objects.filter(username = username)
    if not user:
        return redirect('index')
    if user is not None:
        user = User.objects.get(username = username)
        new_profile_value1 = Profile.objects.get(user_name = user)
        gettoken = new_profile_value1.token
        if gettoken != token:
            return redirect('index')
        context['username'] = user.username
        context['form'] = ChangePasswordForm()
        return render(request, 'grumblr/changepassword.html', context)

def emailconfirmation(request, username, token):
    context = {}
    user = User.objects.get(username = username)
    if not user:
        return redirect('index')
    if user is not None:
        user = User.objects.get(username = username)
        new_profile_value2 = Profile.objects.get(user_name = user)
        gettoken1 = new_profile_value2.token
        if gettoken1 != token:
            return redirect('index')
        user.is_active = True
        user.save()
        login(request, user)
        return redirect('post')


