function submitForm(event, url, successURL = null) {
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
                $(button).html("Data saved <i class='fa fa-check'></i>");
                setTimeout(function () {
                    if (null === successURL || 'null' == successURL)
                        location.reload();
                    else
                        window.location.replace(successURL);

                }, 2000);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (null != jqXHR.responseJSON && null != jqXHR.responseJSON.message)
                    alert(jqXHR.responseJSON.message);
                else alert(textStatus + " " + errorThrown);
            }
        });
}