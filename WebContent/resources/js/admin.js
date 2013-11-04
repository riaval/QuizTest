$(document).ready(function() {

    var input = "<input type='text' />";

    $('.addAnswerBt').click(function() {
      $('#answers').append(input);
    });

});