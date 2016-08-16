require.config({
    paths:{
        jquery: '/assets/lib/jquery/jquery.min',
        bootstrap: '/assets/lib/bootstrap/js/bootstrap.min'
    }
});


require(["todolist", "jquery"], function(tdl, $) {
    $(document).ready(function() {

        tdl.updateToDoList();

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
