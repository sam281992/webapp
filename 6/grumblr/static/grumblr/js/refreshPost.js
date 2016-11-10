var req;
var postId;
var current;


$(document).on('submit', '.blahblah', function(event){
    event.preventDefault();
    current = this.id;
    create_comment();
});

function sendRequest(){
	
	
	
    firstPostId = $("#post div:first").attr("id")
    
    req = jQuery.get( "/refreshpost" , {'data' : firstPostId },function(data){
        handleResponse(data)
    }
    , "json");
}	

function handleResponse(data){
	if (req.readyState != 4 || req.status != 200) {
        return;
    }
    for(var i = data.length - 1; i >= 0  ; --i){
        
        if (data[i]["model"] ==  "grumblr.item"){
            
            date = new Date(data[i]['fields']['datetime'])
            
            postuser = data[i]["fields"]["uname"]
            
            postid = data[i]["pk"]
            
            for( var j = 0; j < data.length; j++){
                
                if (data[j]["model"] ==  "grumblr.profile"){
                    
                    if(data[j]["fields"]["user_name"] == postuser){
                       $("#post").prepend("\
                       		<div class ='postclass' id = "+ data[i]['pk'] + ">\
            				<div class = 'container-fluid'>\
						        <div class='row'>\
                                <div class = 'container-fluid'>\
                                <div class='row'>\
						            <div class='col-lg-6'>\
						                <h5>" + data[i]['fields']['post_text'] + "</h5>\
						            </div>\
						            <div class='col-lg-6'>\
						            <img src='/retrieve_image/"+ data[j]['fields']['username_second']+"' height='50' width='50'>\
						            </div>\
						        </div>\
                                </div>\
						        <div class='container-fluid'>\
                                <div class='row'>\
						            <div class='col-lg-4'>\
						                <h5> " + date.toLocaleString()+ "</h5>\
						            </div>\
						            <div class='col-lg-4 linkclass'>\
						                <a href='/profref/"+ data[j]['fields']['username_second'] +"'>"+ data[j]['fields']['username_second']+"</a>\
						            </div>\
						        </div>\
						         <div class = 'row' id = allcomments"+ data[i]['pk'] +">\
                                </div> \
                                </div>\
							</div>\
							</div>\
                            </div>\
                       	")

                        // Adding Comments for the current post.
                        for (var k = 0; k < data.length; k++){
                            if (data[k]["model"] ==  "grumblr.comment"){
                                date1 = new Date(data[k]["fields"]["datevalue"])
                                commentuser = data[k]["fields"]["uservalue"]
                                if(data[k]["fields"]["postvalue"] == postid){
                                    for( var h = 0; h < data.length; h++){
                                        if (data[h]["model"] ==  "grumblr.profile"){
                                            if(data[h]["fields"]["user_name"] == commentuser){
                                            	$('#'+'allcomments'+postid).append("\
													        <div class= 'container-fluid'>\
                                                            <div class= 'row'>\
                                                            <div class='col-lg-6'> \
													            <h5>"+ data[k]['fields']['textvalue'] + "</h5> \
													        </div> \
													        <div class='col-lg-6'>\
													        <img src='/retrieve_image/"+ data[j]['fields']['username_second']+"' height='50' width='50'>\
													        </div> \
													        <br><br>\
													        <div class='col-lg-4'> \
													            <h5>"+ date1.toLocaleString() + "</h5> \
													        </div> \
													        <div class='col-lg-4 linkclass'> \
													            <a href='/profref/"+ data[j]['fields']['username_second'] +"'>"+ data[j]['fields']['username_second']+"</a>\
													        </div> \
                                                            </div>\
                                                            </div>\
									            ");
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        $('#'+'allcomments' + postid).after("\
                            <div class = 'row'>\
                                <div class = 'col-md-12 blahblah ' id = '" + postid +"'> \
                                    <form action='/addcomment' method='post'>\
                                        <input class='commentform' id='id_comment" + postid +"' maxlength='42' name='comment' placeholder='Add a comment?' required='' type='text'>\
                                        <input id='id_message" + postid +"' name='message' value='" + postid +"' type='hidden'>\
                                        <br><button class='btn btn-primary btn-xs' type='submit'>Add Comment?</button>\
                                    </form>\
                                </div> \
                            </div>\
                        ")
                    }
                }
            }
        }
    }
}

function create_comment() {
    $.ajax({
        url : "/commentPage", 
        type : "POST", 
        data : { comment_text : $('#'+'id_comment'+current).val(), comment_message : $('#'+'id_message'+current).val() },

        success : function(json) {
            $('#'+'id_comment'+current).val(''); 
            $('#'+'allcomments'+current).append("\
                <div class='container-fluid'>\
                <div class='row'> \
				    <div class='container-fluid'>\
				    <div class='row'> \
				        <div class='col-lg-6'> \
				            <h5>"+ json.text + "</h5> \
				        </div> \
				        <div class='col-lg-6'> \
				           <img src='/retrieve_image/"+ json.username +"' height='50' width='50'>\
				        </div> \
				    </div> \
				    </div>\
				    <div class='container-fluid'>\
				    <div class='row'> \
				        <div class='col-lg-4'> \
				            <h5>"+ json.date.toLocaleString() + "</h5> \
				        </div> \
				        <div class='col-lg-4'> \
				            <form action='/profile' method='get'> \
				                <button type='submit' name='req' value="+ json.username + "  class='profbut'>"+ json.username + "</button> \
				            </form> \
				        </div> \
				    </div> \
				    </div>\
				</div>\
                </div>\
            ");
        },
    });
};	


window.setInterval(sendRequest, 5000);

// CSRF
 function getCookie(name) {  
    var cookieValue = null;
    if (document.cookie && document.cookie != '') {
        var cookies = document.cookie.split(';');
        for (var i = 0; i < cookies.length; i++) {
            var cookie = jQuery.trim(cookies[i]);
            // Does this cookie string begin with the name we want?
            if (cookie.substring(0, name.length + 1) == (name + '=')) {
                cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                break;
            }
        }
    }
    return cookieValue;
  }
  var csrftoken = getCookie('csrftoken');
  $.ajaxSetup({
    beforeSend: function(xhr, settings) {
        xhr.setRequestHeader("X-CSRFToken", csrftoken);
    }
  });