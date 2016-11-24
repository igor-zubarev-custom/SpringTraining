
$(document).ready(function () {
    getCartInfo();
});

var SpringTraining = {};
SpringTraining.Product = {
    sendAddToCartJson: function (event, elem) {
        event.preventDefault();
        var id = elem.getAttribute('id');
        var quantity = document.getElementById('quantity_'+id).value;
        var url = document.getElementById('productsForm_'+id).getAttribute('action');        
        SpringTraining.Product.sendProductFormDataJson(id, quantity, url);
    },

    sendProductFormDataJson: function (id, quantity, url) {
        var json = {"id" : id, "quantity" : quantity};
        $('.success').remove();
        $('.error').remove();

        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            url: url,
            data: JSON.stringify(json),
            type: "POST",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader(header, token);
            },
            success: function (response) {
                var messages = response.messageList;
                var responseInfo = "";
                for(i = 0; i < messages.length; i++){
                    responseInfo += messages[i] + ". ";
                }
                $('#quantity_' + id).val('1');
                $('#input_' + id).append('<div class="success">' + responseInfo + '</div>');                
                getCartInfo();
            },
            error: function (xhr) {
                var response = $.parseJSON(xhr.responseText);
                var messages = response.messageList;
                var responseInfo = "";
                for(i = 0; i < messages.length; i++){
                    responseInfo += messages[i] + ". ";
                }
                $('#quantity_' + id).val(quantity);
                $('#input_' + id).append('<div class="error">' + responseInfo + '</div>');
            }
        });
    }
};

SpringTraining.Cart = {
    sendDeleteJson: function (event, elem) {
        event.preventDefault();
        var id = elem.getAttribute('id');
        var url = '/shop/deleteFromCart';
        SpringTraining.Cart.sendDeleteFromCartJson(id, url);
    },

    sendDeleteFromCartJson: function (id, url) {
        var json = {"id" : id};
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            url: url,
            data: JSON.stringify(json),
            type: "POST",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader(header, token);
            },
            success: function (response) {
                $('#row_' + id).remove();
                getCartInfo();               
            }
        });
    }
};

SpringTraining.Admin = {
    sendChangeStatusJson: function (event, elem) {
        event.preventDefault();
        var url = "/admin/changeOrderStatus";
        var id = elem.getAttribute('id');
        var currentStatus = $('#status_' + id).text();
        var changedStatus = "";
        var statusCode = "";
        
        if (currentStatus == "WAITING FOR DELIVERY"){
            statusCode = 0;
            changedStatus = "COMPLETED";            
        };
        if (currentStatus == "COMPLETED"){
            statusCode = 1;
            changedStatus = "WAITING FOR DELIVERY";            
        };
        
        var json = {"orderId" : id, "statusNumber" : statusCode};
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            url: url,
            data: JSON.stringify(json),
            type: "POST",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader(header, token);
            },
            success: function (response) {
                $('#status_' + id).html(changedStatus);               
            }
        });
    }
};



function getCartInfo() {
    $.ajax({
        type: "GET",
        url: "/cartInfo",
        dataType: "json",
        success: function (response) {
            $('.cartMini').empty();
            $('.cartMini').html('My cart: ' + response.quantity + ' items, ' + response.price + '$');
        }
    })
};






