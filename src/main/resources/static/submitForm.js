function submitForm(event, url) {
    event.preventDefault();
    var button = event.target || event.srcElement;
    var formular = $(button).parents("form").first();

    var formData = $(formular).serializeJSON();
    console.log("form data : " + JSON.stringify(formData));
    $.ajax(
        {
            type: 'post',
            url: url,
            headers: {"Content-Type": "application/json"},
            data: JSON.stringify(formData),
            beforeSend: function () {
                $(button).html("Saving...");
            },
            success: function (result) {
                location.reload();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(textStatus + " " + errorThrown);
            }
        });
}