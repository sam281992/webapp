var req;
var postId;
var current;

// Submit comment on submit
$(document).on('submit', '.blahblah', function(event){
    event.preventDefault();
    current = this.id;
    create_comment();
});

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
                            &nbsp;&nbsp;\
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
                            &nbsp;&nbsp;\
                            <h5>"+ json.date + "</h5> \
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
