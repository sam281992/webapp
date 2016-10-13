To turn in homework 4, create files (and subdirectories if needed) in
this directory, add and commit those files to your cloned repository,
and push your commit to your bare repository on GitHub.

Add any general notes or instructions for the TAs to this README file.
The TAs will read this file before evaluating your work.

Name : Samyak Tushar Mehta 
Course : Web Applications Development(15-637)


Attribution
	
	- Images are taken from google images. The images do not have copyright.
	- Bootstrap is designed and built with all the love in the world by @mdo and @fat. Maintained by the core team with the help of our contributors. Code licensed MIT, docs CC BY 3.0. Bootstrap is released under the MIT license and is copyright 2016 Twitter, keeping the license and  copyright notices included in bootstraps CSS and javascript files.
	- Background image is borrowed from act-on which contains the copyright between 2008-2016 on the mentioned image.

HTML Pages
	
	Index.html - This html document is the register document, allowing users to register to the grumblr website. The Index page on clicking the register button will direct users a an email link on the terminal, using the email link will forward users to the posts page logining them in. Apart from this the user cannot get signed up.

	Login.html - This html document is the login page, allowing users to login to the grumblr website to see various posts.
	
	Post.html - This is the global stream page, where posts of all the users can be displayed. These posts are dynamically retrieved from the database.(globalstream). The images are dynamically retrieved from the table and after uploading. The uploaded images are stored in the media folder. The media folder deletes older images when "pip install django-cleanup" is installed through the terminal.
	
	profile.html - The user profile page, consists of the user basic info, posts written by the user and the users image, these posts dynamically update the user profile. The images are dynamically retrieved from the table and after uploading. The default image installed when no image is uploaded by the user is that of the Ironman, the filename is profile.jpg. Users can follow and unfollow other users by clicking on the follow button. On clicking on the follow button on a particular users profile page, the logged in user can start following the other user. Similarly clicking on the unfollow page the user unfollows the other user.
	
	ForgotPassword.html - The ForgotPassword page asks users to enter their registered username and email_id, displays errors if either of the 2 fields gives an error message. If the username and email are valid, the page will send an email and the link can be seen in the terminal.

	ChangePassword.html - The ChangePassword page allows users to change password and is displayed on the screen when the user types the link recieved on the terminal on the webpage.

	Following.html - This page shows posts of all the other users this current logged in user is following. If the current logged in user is not following anyone, this page would be blank.

	Authentication module is implemented ensuring that the right user logs in. The logout module is also implemented. The logged in user cannot read his own post in the global stream page and needs to access his own profile to view his posts.


	Arbitary, random user and posts are incoporated in the application. 
	username :- blah	
	password :- 123 

	References

	1) bootstrap online resources(http://getbootstrap.com)
	2) bootstrap component resources (http://getbootstrap.com/components/)
	3) Stackoverflow (http://stackoverflow.com)

Image References
	
	Images are taken from google images.
	Background image - https://www.act-on.com/blog/2015/06/business-blogging-as-an-equalizer-for-small-and-medium-businesses/