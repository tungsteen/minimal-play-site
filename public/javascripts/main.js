requirejs.config();


requirejs(['jquery'], function($) {
    $(document).ready(function() {
        $("#addToDoEntryForm").submit( function( event ) {
            event.preventDefault();

            var submitForm = $( this ), 
                textForm = submitForm.find("#textToSubmit").val(), 
                url = submitForm.attr("action");

          // Send the data using post
          var addTodoUrl = jsRoutes.controllers.ToDoController.addTodo({done: false, text: textForm});
          var posting = $.post( addTodoUrl.url );
 
          // Put the results in a div
          posting.done(function( data ) {
              console.log("Receive on done: " + data);
              // var content = $( data ).find( "#content" );
              // $( "#result" ).empty().append( content );
          });
        });
    });
});
