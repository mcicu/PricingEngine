$(document).ready(function () {
    var startDate = moment($("#startDateInput").val(), 'YYYY-MM-DD[T]HH:mm').toDate(); //backend date
    $("#startDateInput").datetimepicker({date: null}); //initialize with null (bug workaround)
    $("#startDateInput").datetimepicker('date', startDate);

    var endDate = moment($("#endDateInput").val(), 'YYYY-MM-DD[T]HH:mm').toDate(); //backend date
    $("#endDateInput").datetimepicker({date: null}); //initialize with null (bug workaround)
    $("#endDateInput").datetimepicker('date', endDate);
});