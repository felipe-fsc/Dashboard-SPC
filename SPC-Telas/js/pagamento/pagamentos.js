$(function() {
    google.charts.load('current', {'packages':['corechart']});
  google.charts.setOnLoadCallback(drawChart);

  function drawChart() {

    $.getJSON("http://localhost:9000/pagamentos", function(data) {
        
    }).done(function(data) {
        var myArray = [];
        myArray.push(['Tipo', 'Porcentagem']);
        $.each(data, function(i, item){
            myArray.push([item['statusPagamento'], item['percentual']])
        });
        console.log(myArray);
    

        var data = google.visualization.arrayToDataTable(myArray);

        var options = {

        };''

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
    });
  }
});