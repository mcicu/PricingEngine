$.fn.datetimepicker.Constructor.Default = $.extend({}, $.fn.datetimepicker.Constructor.Default, {
    format: "DD MMM YYYY HH:mm",
    extraFormats: ["YYYY-MM-DD[T]HH:mm"]
});


//override default $.fn.val to return iso8601 localdatetime for datetimepicker input field
(function ($) {
    var originalVal = $.fn.val;
    $.fn.val = function (value) {
        if (arguments.length >= 1) {
            // setter invoked, do nothing
        } else {
            if (this.attr('data-toggle') === 'datetimepicker') {
                var rawString = originalVal.apply(this, arguments);
                var iso8601date = moment(rawString, 'DD MMM YYYY HH:mm').format('YYYY-MM-DD[T]HH:mm');
                if (iso8601date !== 'Invalid date')
                    return iso8601date;
            }
        }
        return originalVal.apply(this, arguments);
    };
})(jQuery);
