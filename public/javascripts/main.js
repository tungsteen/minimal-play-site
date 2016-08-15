requirejs.config();


requirejs(['jquery'], function($) {
    $(document).ready(function() {
        console.log("Document is ready!");
        $("#addButton").click(function() {
            console.log("Send post per ajax!");
        });
    });
});
