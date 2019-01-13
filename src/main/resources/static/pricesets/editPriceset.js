var tiersData = []; //to be loaded
var rowsData = [];

$(document).ready(function () {

    var startDate = moment($("#startDateInput").val(), 'YYYY-MM-DD[T]HH:mm').toDate(); //backend date
    $("#startDateInput").datetimepicker({date: null}); //initialize with null (bug workaround)
    $("#startDateInput").datetimepicker('date', startDate);

    var endDate = moment($("#endDateInput").val(), 'YYYY-MM-DD[T]HH:mm').toDate(); //backend date
    $("#endDateInput").datetimepicker({date: null}); //initialize with null (bug workaround)
    $("#endDateInput").datetimepicker('date', endDate);

    $.ajax({
        "url": "/PricingEngine/rest/offers/{offerId}/tiers".replace("{offerId}", OFFER_ID),
        "method": "GET",
        "success": function (data) {
            tiersData = data;
            getMatrixRows();
        }
    })
});

function getMatrixRows() {
    $.ajax({
        "url": "/PricingEngine/rest/pricesets/{pricesetId}/rows".replace("{pricesetId}", PRICESET_ID),
        "method": "GET",
        "success": function (data) {
            rowsData = data;
            triggerHotRendering();
        }
    })
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
                columnMeta.width = 250;
                columnMeta.className = "htCenter htMiddle";
            } else if (tiersData[column - 1] !== undefined) { //tiers offset by -1 due to products column
                columnMeta.data = "priceCells.priceTier{tierId}.value".replace("{tierId}", tiersData[column - 1].id);
                columnMeta.className = "htCenter htMiddle";
                columnMeta.validator = Handsontable.validators.NumericValidator;
                columnMeta.allowInvalid = false;

            } else columnMeta = null;

            return columnMeta;
        },
        afterChange: saveHotModifiedCells,
        rowHeights: 50,
    });
}

function saveHotModifiedCells(change, source) {
    if (source === "loadData")
        return;
    var modifiedCells = [];
    for (index in change) {
        console.log(change);
        var changedRowId = change[index][0];
        var productId = rowsData[changedRowId].product.id;
        var tierId = change[index][1].replace("priceCells\.priceTier", "").replace("\.value", "");

        //todo check cell state
        var cell = rowsData[changedRowId]["priceCells"]["priceTier" + tierId]; //cell exists because HOT already managed the change locally
        cell.pricesetId = PRICESET_ID;
        cell.productId = productId;
        cell.tierId = tierId;
        modifiedCells.push(cell);
    }

    if (modifiedCells.length == 0) return;
    $.ajax({
        "url": "/PricingEngine/rest/pricesets/{pricesetId}/cells/save".replace("{pricesetId}", PRICESET_ID),
        "method": "POST",
        "headers": {"Content-Type": "application/json"},
        "data": JSON.stringify(modifiedCells),
        "success": function (data) {
            console.log("cells saved");
        }
    })
}
