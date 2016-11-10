# -*- coding: utf-8 -*-
# Generated by Django 1.10.1 on 2016-10-22 23:05
from __future__ import unicode_literals

from django.conf import settings
from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
    ]

    operations = [
        migrations.CreateModel(
            name='Item',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('post_text', models.CharField(max_length=42)),
                ('datetime', models.DateTimeField(auto_now=True)),
                ('uname', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to=settings.AUTH_USER_MODEL)),
            ],
            options={
                'ordering': ['-datetime'],
            },
        ),
        migrations.CreateModel(
            name='Profile',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('age', models.IntegerField(default=0, null=True)),
                ('bio', models.CharField(default='', max_length=200, null=True)),
                ('image', models.ImageField(default='profile.jpg', upload_to=b'')),
                ('token', models.CharField(default='', max_length=200)),
                ('follower', models.ManyToManyField(related_name='_profile_follower_+', to=settings.AUTH_USER_MODEL)),
                ('user_name', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to=settings.AUTH_USER_MODEL)),
            ],
        ),
    ]
