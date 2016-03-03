$(document).ready(function () {
    var getIpmInv = function () {
        ShowDiv('fade');
        var scItemId = $("#getIpmInv_text").val();

        var params = {
            scItemId: scItemId
        };
        $.ajax({
            url: '/tools/www/get_ipm_inv_json.do?_input_charset=utf-8',
            data: params,
            type: 'post',
            dataType: 'json',
            success: function (data) {
                if (data.code == "200") {
                    var resultMsg = data.msg + "<br>";
                    var mapJson = data.data;
                    var dataObj = JSON.stringify(mapJson);
                    var map = eval("(" + dataObj + ")");

                    var resultData = "";
                    for (var key in map) {
                        var resultTable = "货品itemId=" + key + "库存信息如下<br>" +
                            "<table  class=\"table table-striped table-hover table-bordered\" style=\"width: 80%;\">" +
                            "<tr>" +
                            "<td>storeCode</td>" +
                            "<td>quantity</td>" +
                            "<td>withholdingQuantity</td>" +
                            "<td>occupyQuantity</td>" +
                            "<td>sellableQuantity</td>" +
                            "<td>availableQuantity</td>" +
                            "<td>storeType</td>" +
                            "</tr>";

                        var cMap = map[key];

                        for (var store in cMap) {
                            resultTable = resultTable +
                                "<tr>" +
                                "<td>" + store + "</td>" +
                                "<td>" + cMap[store].quantity + "</td>" +
                                "<td>" + cMap[store].withholdingQuantity + "</td>" +
                                "<td>" + cMap[store].occupyQuantity + "</td>" +
                                "<td>" + cMap[store].sellableQuantity + "</td>" +
                                "<td>" + cMap[store].availableQuantity + "</td>" +
                                "<td>" + cMap[store].storeType + "</td>" +
                                "</tr>";
                        }
                        resultTable = resultTable + "</table><br>";
                        resultData = resultData + resultTable;
                    }

                    $("#resultMessageShow").html("后端货品" + scItemId + "库存查询结果如下:<br>" + resultMsg + resultData);
                    parent.Show2Div('MyDiv', 'fade');
                } else {
                    $("#resultMessageShow").html("查询失败！" + data.msg);
                    parent.Show2Div('MyDiv', 'fade');
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("系统错误!执行失败！");
                CloseDiv('fade');
            }
        });
    };
});