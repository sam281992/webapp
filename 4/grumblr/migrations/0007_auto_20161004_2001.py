# -*- coding: utf-8 -*-
# Generated by Django 1.10.1 on 2016-10-05 00:01
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('grumblr', '0006_auto_20161004_1946'),
    ]

    operations = [
        migrations.AlterField(
            model_name='profile',
            name='image',
            field=models.ImageField(default='images/profile.jpg', upload_to='/static/grumblr/'),
        ),
    ]