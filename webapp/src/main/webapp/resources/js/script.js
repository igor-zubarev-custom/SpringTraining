window.onload = getCartInfo();

function sendAddToCartJson(event, elem) {
    event.preventDefault();
    var id = elem.getAttribute('id');
    var quantity = document.getElementById('quantity_'+id).value;
    var url = document.getElementById('productsForm_'+id).getAttribute('action');
    sendProductFormDataJson(id, quantity, url);
}

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
}

function sendProductFormDataJson(id, quantity, url) {
    var json = {"id" : id, "quantity" : quantity};
    $('.success').removeClass('success');
    $('.error').removeClass('error');

    $.ajax({
        url: url,
        data: JSON.stringify(json),
        type: "POST",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function (response) {
            var messages = response.messageList;
            var responseInfo = "";
            for(i = 0; i < messages.length; i++){
                responseInfo += messages[i] + ". ";
            }
            $('#quantity_' + id).val('');
            document.getElementById('quantity_' + id).placeholder=responseInfo;
            $('#row_' + id).addClass('success');
            getCartInfo();
        },
        error: function (xhr) {
            var response = $.parseJSON(xhr.responseText);
            var messages = response.messageList;
            var responseInfo = "";
            for(i = 0; i < messages.length; i++){
                responseInfo += messages[i] + ". ";
            }
            $('#quantity_' + id).val('');
            document.getElementById('quantity_' + id).placeholder=responseInfo;
            $('#row_' + id).addClass('error');
        }
    });
}


function sendDeleteJson(event, elem) {
    event.preventDefault();
    var id = elem.getAttribute('id');
    var url = document.getElementById('cartItemForm_'+id).getAttribute('action');
    sendDeleteFromCartJson(id, url);
}

function sendDeleteFromCartJson(id, url) {
    var json = {"id" : id};

    $('.success').empty();
    $('.error').empty();
    $('.success').removeClass('success');
    $('.error').removeClass('error');

    $.ajax({
        url: url,
        data: JSON.stringify(json),
        type: "POST",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function (response) {
            var messages = response.messageList;
            var responseInfo = "";
            for(i = 0; i < messages.length; i++){
                responseInfo += messages[i] + ". ";
            }
            $('#info').html(responseInfo);
            $('#row_' + id).remove();
            getCartInfo();
        },
        error: function (xhr) {
            var response = $.parseJSON(xhr.responseText);
            var messages = response.messageList;
            var responseInfo = "";
            if (xhr.status = 400){
                for(i = 0; i < messages.length; i++){
                    responseInfo += messages[i] + ". ";
                }
            };
            if (xhr.status = 406){
                for(i = 0; i < messages.length; i++){
                    responseInfo += messages[i] + ". ";
                }
            };
            $('#info').html(responseInfo);
        }
    });
}



