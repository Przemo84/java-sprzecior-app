$(function () {
    $('[data-toggle="tooltip"]').tooltip({
        placement: 'top'
    })
    $('[data-toggle="tooltip-left"]').tooltip({
        placement: 'left'
    })

    /**
     * side menu
     */
    $('.menu-list > a').click(function () {
        var parent = $(this).parent();
        var sub = parent.find('> ul');
        if (!$('body').hasClass('sidebar-collapsed')) {
            if (sub.is(':visible')) {
                sub.slideUp(300, function () {
                    parent.removeClass('nav-active');
                });
            } else {
                visibleSubMenuClose();
                parent.addClass('nav-active');
                sub.slideDown(300);
            }
        }
        return false;
    });

    function visibleSubMenuClose() {
        jQuery('.menu-list').each(function () {
            var t = jQuery(this);
            if (t.hasClass('nav-active')) {
                t.find('> ul').slideUp(300, function () {
                    t.removeClass('nav-active');
                });
            }
        });
    }

    jQuery('.side-navigation > li').hover(function () {
        jQuery(this).addClass('nav-hover');
    }, function () {
        jQuery(this).removeClass('nav-hover');
    });
    jQuery('.toggle-btn').click(function () {
        var body = jQuery('body');
        var bodyposition = body.css('position');
        if (bodyposition != 'relative') {
            if (!body.hasClass('sidebar-collapsed')) {
                body.addClass('sidebar-collapsed');
                jQuery('#sidebar-hide').addClass('toggle-btn-hide');
                jQuery('.side-navigation ul').attr('style', '');
            } else {
                body.removeClass('sidebar-collapsed');
                jQuery('.side-navigation li.active ul').css({
                    display: 'block'
                });
                jQuery('.side-navigation > li > a > span').css('display', 'none');
                jQuery('.side-navigation .child-list').css('opacity', '0');
                jQuery('.side-navigation > li > a > span').fadeIn(1000);
                jQuery('.side-navigation .child-list').animate({opacity: 1}, 1000);
                jQuery('#sidebar-hide').removeClass('toggle-btn-hide');
            }
        }
    });

    $('.notification').click(function () {
        if (!$(document).find('.notification-dropdown').hasClass('dd')) {
            hide_dropdown();
        }
        else {
            $('.notification-dropdown').removeClass('dd').addClass('dropdown-transition')
        }
    });


    // handler to close dropdown on clicking outside of it
    $(document).on('click', function (e) {
        var target = $(e.target);
        if (!target.closest('.notification').length && !target.closest('.dropdown-transition').length) {
            if (!$(document).find('.notification-dropdown').hasClass('dd')) {
                hide_dropdown();
            }
        }
    });

    // function to close dropdown
    function hide_dropdown() {
        $(document).find('.notification-dropdown').removeClass('dropdown-transition').addClass('dd');
        $(document).find('.notification-dropdown').find('.list-item').addClass('background-white');
    }


});


var cropperInstance;

function stopCrop() {

    if (cropperInstance != null && cropperInstance !== undefined) {
        cropperInstance.clear();
        cropperInstance.disable();
        cropperInstance.destroy();

        $('#imgX').val(null);
        $('#imgY').val(null);
        $('#imgWidth').val(null);
        $('#imgHeight').val(null);
    }
}

function startCrop(aspectRatio, button) {
    var jqButton = $(button);


    if(jqButton.hasClass("on-crop-mode")){
        jqButton.removeClass("on-crop-mode");
        button.innerHTML = "<i class=\"fa fa-crop\"></i> Dopasuj rozmiar";

        stopCrop();
        return;
    } else {
        jqButton.addClass("on-crop-mode");
        button.innerHTML = "<i class=\"fa fa-crop\"></i> Anuluj dopasowanie";
    }



    var image = document.querySelector('#image');
    var minAspectRatio = aspectRatio;
    var maxAspectRatio = aspectRatio;
    cropperInstance = new Cropper(image, {
        aspectRatio: aspectRatio,
        viewMode: 1,
        movable: false,
        zoomable: false,
        rotatable: false,
        scalable: false,
        ready: function () {
            var cropper = this.cropper;
            var containerData = cropper.getContainerData();
            var cropBoxData = cropper.getCropBoxData();
            var aspectRatio = cropBoxData.width / cropBoxData.height;
            var newCropBoxWidth;
            if (aspectRatio < minAspectRatio || aspectRatio > maxAspectRatio) {
                newCropBoxWidth = cropBoxData.height * ((minAspectRatio + maxAspectRatio) / 2);
                cropper.setCropBoxData({
                    left: (containerData.width - newCropBoxWidth) / 2,
                    width: newCropBoxWidth
                });
            }
        },
        cropmove: function () {

            console.log('move');

            // var cropper = this.cropper;
            // var cropBoxData = cropper.getCropBoxData();
            // var aspectRatio = cropBoxData.width / cropBoxData.height;
            // if (aspectRatio < minAspectRatio) {
            //     cropper.setCropBoxData({
            //         width: cropBoxData.height * minAspectRatio
            //     });
            // } else if (aspectRatio > maxAspectRatio) {
            //     cropper.setCropBoxData({
            //         width: cropBoxData.height * maxAspectRatio
            //     });
            // }
        },

        crop: function (event) {
            $('#imgX').val(event.detail.x);
            $('#imgY').val(event.detail.y);
            $('#imgWidth').val(event.detail.width);
            $('#imgHeight').val(event.detail.height);
        }

    });

}

