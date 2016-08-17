"use strict";

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
            tdl.addTodoEntry($(this));
        });

        $("td").hover(function() { $(this).css("background-color", "red"); }, 
                      function() { $(this).css("background-color", "white"); });
    });
});
