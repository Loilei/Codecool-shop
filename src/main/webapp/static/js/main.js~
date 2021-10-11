addToCart();


function addToCart() {
    let addToCartButtons = document.getElementsByClassName("btn btn-success");
    // addToCartButtons[0].addEventListener("click",function() {console.log("Working")})
    for (let button of addToCartButtons) {
        console.log(button)
        button.addEventListener("click", async function () {
            console.log("Working")
            // let productId = button.getAttribute("data-product-id")
            let productId = 10;
            console.log(productId);
            await addToCartRequest({productId: productId});
            //change number for cart on navbar
        })
    }
}
async function apiPost2(url, payload) {
    let response = await fetch(url, {
        method: 'POST',
        headers:{
        'Content-Type': 'application/json'
    },
        body: JSON.stringify(payload)
    })
    if (response.status === 200) {
        let data = response.json()
        return data
    }
}
async function addToCartRequest(productId) {
    let response = await apiPost2('/cart', productId)
    return response
}