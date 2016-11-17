// jQuery please
$(document).ready(function () {
    getCartInfo();
})
//window.onload = getCartInfo();

// use 'JS' classes or namespaces
// SpringTraining.Product {
//   addToCart()
//   ...
// }
// SpringTraining.Cart {
//   updateCart()
//   ...
// }

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
}

SpringTraining.Cart = {
    sendDeleteJson: function (event, elem) {
        event.preventDefault();
        var id = elem.getAttribute('id');
        var url = '/deleteFromCart';
        SpringTraining.Cart.sendDeleteFromCartJson(id, url);
    },

    sendDeleteFromCartJson: function (id, url) {
        var json = {"id" : id};
        $.ajax({
            url: url,
            data: JSON.stringify(json),
            type: "POST",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success: function (response) {
                location.reload();
            }
        });
    }
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






