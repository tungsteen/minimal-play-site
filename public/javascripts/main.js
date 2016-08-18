"use strict";

require.config({
    paths:{
        jquery: '/assets/lib/jquery/jquery.min',
        bootstrap: '/assets/lib/bootstrap/js/bootstrap.min'
    }
});

require(["todolist", "jquery"], function(tdl, $) {
    $(window).ready(function() {
        console.log("after refresh!");

        $("#add-form").submit( function( event ) {
            event.preventDefault();
            tdl.addTodoEntry($(this));
        });

        $(".todo-table").on('click', '.delete-button', function(event) { 
            event.preventDefault(); 
            event.stopPropagation(); 
            tdl.deleteTodoEntry($(this)); 
        } );

        $(".todo-table").on('click', '.todo-row', function(event) { 
            event.preventDefault();
            tdl.doneTodoEntry($(this));
        });

        $(".todo-table").on('mouseenter', '.todo-row', function(event) {
            if ( $(this).find("input").is(":checked") ) {
                $(this).find("span").css("visibility", "visible"); } 
        });

        $(".todo-table").on('mouseleave', '.todo-row', function(event) {
            if ( $(this).find("input").is(":checked") ) {
                $(this).find("span").css("visibility", "hidden"); } 
        });

    });
});
