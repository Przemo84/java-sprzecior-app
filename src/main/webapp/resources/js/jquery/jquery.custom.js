$.datepicker.regional['pl'] = {
    closeText: 'Zamknij',
    prevText: '&#x3c;Poprzedni',
    nextText: 'Następny&#x3e;',
    currentText: 'Dziś',
    monthNames: ['Styczeń', 'Luty', 'Marzec', 'Kwiecień', 'Maj', 'Czerwiec', 'Lipiec', 'Sierpień', 'Wrzesień', 'Październik', 'Listopad', 'Grudzień'],
    monthNamesShort: ['Sty', 'Lut', 'Mar', 'Kwi', 'Maj', 'Cze', 'Lip', 'Sie', 'Wrz', 'Paź', 'Lis', 'Gru'],
    dayNames: ['Niedziela', 'Poniedziałek', 'Wtorek', 'Środa', 'Czwartek', 'Piątek', 'Sobota'],
    dayNamesShort: ['Nd', 'Pn', 'Wt', 'Śr', 'Czw', 'Pt', 'So'],
    dayNamesMin: ['Nd', 'Pn', 'Wt', 'Śr', 'Cz', 'Pt', 'So'],
    weekHeader: 'Tydz',
    dateFormat: 'yy-mm-dd',
    firstDay: 1,
    isRTL: false,
    showMonthAfterYear: false,
    yearSuffix: ''
};
$.datepicker.setDefaults($.datepicker.regional['pl']);

(function ($) {
    $.fn.retina = function (retina_part) {
        // Set default retina file part to '-2x'
        // Eg. some_image.jpg will become some_image-2x.jpg
        var settings = {'retina_part': '-2x'};
        if (retina_part)
            jQuery.extend(settings, {'retina_part': retina_part});
        if (window.devicePixelRatio >= 2) {
            this.each(function (index, element) {
                if (!$(element).attr('src'))
                    return;

                var checkForRetina = new RegExp("(.+)(" + settings['retina_part'] + "\\.\\w{3,4})");
                if (checkForRetina.test($(element).attr('src')))
                    return;

                var new_image_src = $(element).attr('src').replace(/(.+)(\.\w{3,4})$/, "$1" + settings['retina_part'] + "$2");
                $.ajax({
                    url: new_image_src, type: "HEAD", success: function () {
                        $(element).attr('src', new_image_src);
                    }
                });
            });
        }
        return this;
    }
})(jQuery);


function confirm_action(redirect_uri) {
    bootbox.confirm({
        title: '<h3>Uwaga</h3>',
        message: '<p>Czy na pewno chcesz wykonać wybraną akcję?</p>',
        buttons: {
            confirm: {
                label: 'OK',
                className: 'btn-primary'
            },
            cancel: {
                label: 'Anuluj',
                className: 'btn'
            }
        },
        callback: function (result) {
            if (result)
            window.location.href = redirect_uri;
        }

    });
}

function confirm_mass_action(redirect_uri) {
    bootbox.confirm({
        title: '<h3>Uwaga</h3>',
        message: '<p>Czy na pewno chcesz wykonać wybraną akcję?</p>',
        buttons: {
            confirm: {
                label: 'OK',
                className: 'btn-primary'
            },
            cancel: {
                label: 'Anuluj',
                className: 'btn'
            }
        },
        callback: function (result) {
            console.log(result)
            if (result)
                $('#executable-users-list-form').submit();
        }
    });

}

function confirm_action_custom(message, redirect_uri) {
    bootbox.dialog(message, [
        {
            "label": "OK",
            "class": "btn-primary",
            "callback": function () {
                window.location.href = redirect_uri;
            }
        }, {
            "label": "Anuluj",
            "class": "btn"
        }
    ], {
        "header": "Uwaga",
        onEscape: function () {
        }
    });
}

function display_alert(message, title) {
    bootbox.dialog(message, [
        {
            "label": "Zamknij",
            "class": "btn"
        }
    ], {
        "header": title,
        onEscape: function () {
        }
    });
}

function delete_setting_upload(id) {
    $('#file_upload_hidden_' + id).val('');
    $('#iconQueue_' + id).remove();
}

function f(x) {
    if (document.getElementById(x).style.display == 'none') {
        document.getElementById(x).style.display = "block";
    } else {
        document.getElementById(x).style.display = "none";
    }
}

function finline(x) {
    if (document.getElementById(x).style.display == 'none') {
        document.getElementById(x).style.display = "";
    } else {
        document.getElementById(x).style.display = "none";
    }
}

function z1(x, w) {
    if (document.getElementById(x)) {
        document.getElementById(x).style.display = 'block';
    }
    if (document.getElementById(w)) {
        document.getElementById(w).style.display = 'none';
    }
}

function z2(x, w, e) {
    if (document.getElementById(x)) {
        document.getElementById(x).style.display = 'block';
    }
    if (document.getElementById(w)) {
        document.getElementById(w).style.display = 'none';
    }
    if (document.getElementById(e)) {
        document.getElementById(e).style.display = 'none';
    }
}

function z3(x, w, e, b) {
    if (document.getElementById(x)) {
        document.getElementById(x).style.display = 'block';
    }
    if (document.getElementById(w)) {
        document.getElementById(w).style.display = 'none';
    }
    if (document.getElementById(e)) {
        document.getElementById(e).style.display = 'none';
    }
    if (document.getElementById(b)) {
        document.getElementById(b).style.display = 'none';
    }
}

$(document).ready(function () {

    $(".retina-ready").retina("@2x");
});

/* Check all table rows */
var checkflag = "false";

function check(field) {
    if (checkflag == "false") {
        for (i = 0; i < field.length; i++) {
            field[i].checked = true;
        }
        checkflag = "true";
        return "check_all";
    } else {
        for (i = 0; i < field.length; i++) {
            field[i].checked = false;
        }
        checkflag = "false";
        return "check_none";
    }
}

function check_all_box(form) {
    var checked = 0;
    if (form) {
        for (var i = 0; i < form.length; i++) {
            if (form[i].value != 'check_none') {
                if (form[i].type == 'checkbox') {
                    form[i].checked = !form[i].checked;
                }
            }
        }
    }
}