var tiersData = []; //to be loaded

function hotModifiedCells(change, source) {
    if (source === 'loadData') {
        return; //don't save this change
    }

    console.log("Change " + change);
    console.log("Source " + source);
}

function triggerHotRendering() {
    var hotElement = document.getElementById("pricesetmatrix");
    var data = [{
        "product": {"id": "1", "name": "iPhone"}, "priceTier2": 111.22
    }];
    hotElement.innerHTML = "";
    var hot = new Handsontable(hotElement, {
        data: data,
        colHeaders: ["Products"].concat(tiersData.map(t => t.name)),
        columns: function (column) {
            var columnMeta = {};
            if (column === 0) {
                columnMeta.data = "product.name";
                columnMeta.readOnly = true;
            }
            else if (tiersData[column - 1] !== undefined) //tiers offset by -1 due to products column
                columnMeta.data = "priceTier" + tiersData[column - 1].id;
            else columnMeta = null;

            return columnMeta;
        },
        afterChange: hotModifiedCells
    });
}


$(document).ready(function () {
    $.ajax({
        "url": "/PricingEngine/rest/offers/" + OFFER_ID + "/tiers",
        "method": "GET",
        "success": function (data) {
            tiersData = data;
            triggerHotRendering();
            console.log("DONE");
        }
    })

});