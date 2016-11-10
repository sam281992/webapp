from django import forms

from django.contrib.auth.models import User

class RegistrationForm(forms.Form):
    first_name = forms.CharField(max_length = 20, widget = forms.TextInput(attrs={'class' : 'form-control'}))
    last_name = forms.CharField(max_length = 20, widget = forms.TextInput(attrs={'class' : 'form-control'}))
    username = forms.CharField(max_length = 20, widget = forms.TextInput(attrs={'class' : 'form-control'}))
    email_id = forms.EmailField(max_length = 30, widget = forms.TextInput(attrs={'class' : 'form-control'}))
    password1 = forms.CharField(max_length = 200, 
                                label='Password', 
                                widget = forms.PasswordInput(attrs={'class' : 'form-control'}))
    password2 = forms.CharField(max_length = 200, 
                                label='Confirm password',  
                                widget = forms.PasswordInput(attrs={'class' : 'form-control'}))


    # Customizes form validation for properties that apply to more
    # than one field.  Overrides the forms.Form.clean function.
    def clean(self):
        # Calls our parent (forms.Form) .clean function, gets a dictionary
        # of cleaned data as a result
        cleaned_data = super(RegistrationForm, self).clean()

        # Confirms that the two password fields match
        password1 = cleaned_data.get('password1')
        password2 = cleaned_data.get('password2')
        if password1 and password2 and password1 != password2:
            raise forms.ValidationError("Passwords did not match.")

        # Generally return the cleaned data we got from our parent.
        return cleaned_data



    # Customizes form validation for the username field.
    def clean_username(self):
        # Confirms that the username is not already present in the
        # User model database.
        username = self.cleaned_data.get('username')
        if User.objects.filter(username=username):
            raise forms.ValidationError("Username is already taken.")

        # Generally return the cleaned data we got from the cleaned_data
        # dictionary
        return username

class LoginForm(forms.Form):
    username = forms.CharField(max_length = 20, widget = forms.TextInput(attrs={'class' : 'form-control'}))
    password1 = forms.CharField(max_length = 200, 
                                label='Password', 
                                widget = forms.PasswordInput(attrs={'class' : 'form-control'}))

    # Customizes form validation for properties that apply to more
    # than one field.  Overrides the forms.Form.clean function.
    def clean(self):
        # Calls our parent (forms.Form) .clean function, gets a dictionary
        # of cleaned data as a result
        cleaned_data = super(LoginForm, self).clean()
        return cleaned_data

class PostForm(forms.Form):
    postFeed = forms.CharField(max_length = 42, widget = forms.TextInput(attrs={'class' : 'form-control', 'placeholder':'Post Something'}))
    

    # Customizes form validation for properties that apply to more
    # than one field.  Overrides the forms.Form.clean function.
    def clean(self):
        # Calls our parent (forms.Form) .clean function, gets a dictionary
        # of cleaned data as a result
        cleaned_data = super(PostForm, self).clean()
        return cleaned_data

class EditProfileForm(forms.Form):
    first_name = forms.CharField(max_length = 20, widget = forms.TextInput(attrs={'class' : 'form-control'}))
    last_name = forms.CharField(max_length = 20, widget = forms.TextInput(attrs={'class' : 'form-control'}))
    password1 = forms.CharField(max_length = 20, 
                                label='Password', 
                                widget = forms.PasswordInput(attrs={'class' : 'form-control'}))
    age = forms.IntegerField(widget = forms.NumberInput(attrs={'class' : 'form-control'}))
    bio = forms.CharField(max_length = 300, widget = forms.TextInput(attrs={'class' : 'form-control'}))

    def clean(self):
        cleaned_data = super(EditProfileForm, self).clean()
        return cleaned_data

class ImageForm(forms.Form):
    imageFile = forms.ImageField()

    def clean(self):
        cleaned_data = super(ImageForm, self).clean()
        return cleaned_data

class ForgotPasswordForm(forms.Form):
    username = forms.CharField(max_length = 20, widget = forms.TextInput(attrs={'class' : 'form-control'}))
    email_id = forms.EmailField(max_length = 30, widget = forms.TextInput(attrs={'class' : 'form-control'}))
    

    def clean(self):
        # Calls our parent (forms.Form) .clean function, gets a dictionary
        # of cleaned data as a result
        cleaned_data = super(ForgotPasswordForm, self).clean()

        usernameClean = self.cleaned_data.get('username')
        emailClean = self.cleaned_data.get('email_id')
        usernameTable = User.objects.filter(username = usernameClean)

        if not usernameTable:
            raise forms.ValidationError("Not a registered username")
        
        currentUserCheck = usernameTable[0]
        print currentUserCheck.email
        if not emailClean == currentUserCheck.email:
            raise forms.ValidationError("Not a registered email")
        return cleaned_data

class ChangePasswordForm(forms.Form):
    password1 = forms.CharField(max_length = 200, 
                                label='Password', 
                                widget = forms.PasswordInput(attrs={'class' : 'form-control'}))
    password2 = forms.CharField(max_length = 200, 
                                label='Confirm password',  
                                widget = forms.PasswordInput(attrs={'class' : 'form-control'}))


    # Customizes form validation for properties that apply to more
    # than one field.  Overrides the forms.Form.clean function.
    def clean(self):
        # Calls our parent (forms.Form) .clean function, gets a dictionary
        # of cleaned data as a result
        cleaned_data = super(ChangePasswordForm, self).clean()

        # Confirms that the two password fields match
        password1 = cleaned_data.get('password1')
        password2 = cleaned_data.get('password2')
        if password1 and password2 and password1 != password2:
            raise forms.ValidationError("Passwords did not match.")

        # Generally return the cleaned data we got from our parent.
        return cleaned_data

class CommentForm(forms.Form):
    comment = forms.CharField(max_length = 20, widget = forms.TextInput(attrs={'class' : 'form-control'}))
    message = forms.IntegerField(widget = forms.HiddenInput)

    def clean(self):
        cleaned_data = super(AddComment, self).clean()
        return cleaned_data



