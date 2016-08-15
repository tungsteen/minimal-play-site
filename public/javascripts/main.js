requirejs.config();


requirejs(['jquery'], function($) {
    $(document).ready(function() {
        console.log("Document is ready!");
        $("#addToDoEntryForm").submit( function( event ) {
            event.preventDefault();

            var submitForm = $( this ), 
                textForm = submitForm.find("#textToSubmit").val(), 
                url = submitForm.attr("action");

            console.log("Send post to ajax! " + textForm);

          /*  
          // Send the data using post
          var posting = $.post( url, { s: term } );
 
          // Put the results in a div
          posting.done(function( data ) {
              console.log("Send post to ajax! " + textForm);
              // var content = $( data ).find( "#content" );
              // $( "#result" ).empty().append( content );
          });
          */  
        });
    });
});
