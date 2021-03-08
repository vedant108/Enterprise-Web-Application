google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback(drawChart);

$("#clickhere").click(function () {
     $("#clickhere").hide();
    $.ajax({
        url: "Inventory",
        type: "POST",
        data: "{}",
        success: function (msg) {
            createDataTable(msg)            
        },
        error: function(){
            console.log("error occurred while making ajax call;")
        }
    });    
});

function createDataTable(jsonData) {
    var parsedData = $.parseJSON(jsonData);
    var data = new Array();
    var productNameArr = new Array();
    var quantityArr = new Array();

    console.log(parsedData);
    var actualData = parsedData.myArrayList;
    data2 = ['Service Name', 'Availability'];
    productNameArr.push(data2);    
    for(var i=0; i<actualData.length; i++) {
      console.log(actualData[i]);
        var productName = actualData[i].map.product_name;
        var quantity = actualData[i].map.product_quantity;
        var data1 = [];
        data1.push(productName);
        data1.push(quantity);
        productNameArr.push(data1);
     }
     console.log(productNameArr)
     drawChart(data, productNameArr);
}
function drawChart(data, productNameArr) {
  console.log(productNameArr);
  var chartData = google.visualization.arrayToDataTable(productNameArr);

  var materialOptions = {
    'width':700,
    'height':2000, 
    chart: {
      title: 'Services Availability BarChart',
      subtitle: 'Based on the available slots'
    },
    hAxis: {
      title: 'Availability',
      minValue: 0,
    },
    vAxis: {
      title: 'Service Name'
    },
    bars: 'horizontal',
    axes: {
      y: {
        0: {side: 'right'}
      }
    }
    };
  var materialChart = new google.charts.Bar(document.getElementById('chart_div'));
  materialChart.draw(chartData, materialOptions);
}