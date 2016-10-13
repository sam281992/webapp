from __future__ import unicode_literals
from datetime import datetime
from django.db import models
from django.contrib.auth.models import User


# Create your models here.
class Item(models.Model):
    post_text = models.CharField(max_length = 42)
    uname = models.ForeignKey(User)
    datetime = models.DateTimeField(auto_now="True")


    class Meta:
        ordering =["-datetime"]

    def __unicode__(self):
        return self.post_text

class Profile(models.Model):
    age = models.IntegerField(null = True, default = 0)
    user_name = models.ForeignKey(User)
    bio = models.CharField(max_length = 200, null = True, default = "")
    image = models.ImageField(default = 'profile.jpg')
    follower = models.ManyToManyField(User, symmetrical = False, related_name="follow+")
    token = models.CharField(max_length = 200, default = '')
    def __unicode__(self):
        return '%s %s' % (self.user_name,self.bio)
