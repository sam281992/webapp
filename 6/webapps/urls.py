"""webapps URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.10/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.conf.urls import url, include
    2. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""
from django.conf.urls import *
from django.contrib import *
from django.contrib.auth import views as auth_views
from django. conf import settings
from django.conf.urls.static import static
from django import views as stat_views
import grumblr.views


urlpatterns = [
    
    url(r'^post$', grumblr.views.post, name= 'post'),
    url(r'^commentPage$',grumblr.views.postComment, name= 'commentPage'),
    url(r'^refreshpost$', grumblr.views.postRefresh, name= 'refreshpost'),
    url(r'^refreshprofile$', grumblr.views.profileRefresh, name= 'refreshprofile'),
    url(r'^profile$', grumblr.views.profile, name= 'profile'),
    url(r'^index$', grumblr.views.reg, name= 'index'),
    url(r'^login$', grumblr.views.login_page, name= 'login'),
    url(r'^logout$', auth_views.logout_then_login, name= 'logout'), 
    url(r'^editprofile$', grumblr.views.editprofile, name= 'editprofile'),
    url(r'^forgotPassword$',grumblr.views.forgotPassword, name= 'forgotPassword'),
    url(r'^emailconfirmation/(?P<username>[\w.@+-]+)/(?P<token>[\w.@+-]+)$', grumblr.views.emailconfirmation, name = 'emailconfirmation'),
    url(r'^profref/(?P<username>[\w.@+-]+)', grumblr.views.profref, name = 'profref'),
    url(r'^confirm/(?P<username>[\w.@+-]+)/(?P<token>[\w.@+-]+)$', grumblr.views.renderChangePasswordPage, name = 'confirm'),
    url(r'^changepassword$', grumblr.views.changepassword, name= 'changePassword'),
    url(r'^displayfollower$', grumblr.views.displayfollow, name= 'displayfollower'),  
    url(r'^retrieve_image/(?P<username>[\w.@+-]+)$',grumblr.views.retrieve_image, name='retrieve_image'),  
    url(r'^$', grumblr.views.login_page),
    url(r'^media/(?P<path>.*)$', stat_views.static.serve, {'document_root': settings.MEDIA_ROOT, 'show_indexes': True}),
    url(r'^',grumblr.views.login_page),

]

