var tiersData = []; //to be loaded
var rowsData = [];

function getMatrixRows() {
    $.ajax({
        "url" : "/PricingEngine/rest/pricesets/{pricesetId}/rows".replace("{pricesetId}", PRICESET_ID),
        "method" : "GET",
        "success" : function(data) {
            rowsData = data;
            triggerHotRendering();
        }
    })
}

function hotModifiedCells(change, source) {
    if (source === 'loadData') {
        return; //don't save this change
    }

    console.log("Change " + change);
    console.log("Source " + source);
}

function triggerHotRendering() {
    var hotElement = document.getElementById("pricesetmatrix");

    hotElement.innerHTML = "";
    var hot = new Handsontable(hotElement, {
        data: rowsData,
        colHeaders: ["Products"].concat(tiersData.map(t => t.name)),
        columns: function (column) {
            var columnMeta = {};
            if (column === 0) {
                columnMeta.data = "product.name";
                columnMeta.readOnly = true;
            }
            else if (tiersData[column - 1] !== undefined) //tiers offset by -1 due to products column
                columnMeta.data = "priceCells.priceTier{tierId}.value".replace("{tierId}", tiersData[column - 1].id);
            else columnMeta = null;

            return columnMeta;
        },
        afterChange: hotModifiedCells
    });
}

$(document).ready(function () {
    $.ajax({
        "url": "/PricingEngine/rest/offers/{offerId}/tiers".replace("{offerId}", OFFER_ID),
        "method": "GET",
        "success": function (data) {
            tiersData = data;
            getMatrixRows();
        }
    })

});