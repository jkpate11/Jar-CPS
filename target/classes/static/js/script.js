async function fetchJarInfo() {
    try {
        const response = await fetch("/jars/1"); 
        if (!response.ok) {
            throw new Error("Failed to fetch jar information");
        }
        const jar = await response.json();
        document.getElementById("jar-name").innerText = jar.name;
        document.getElementById("quantity").innerText = jar.quantity;
        updateJarVisual(jar.quantity);
        if (jar.quantity >= 200) {
            document.getElementById("notification").style.display = "none";
        }
    } catch (error) {
        console.error(error);
    }
}

function updateJarVisual(quantity) {
    const jarHeight = 400; 
    const maxQuantity = 1000; 
    const liquidHeight = (quantity / maxQuantity) * jarHeight;
    document.getElementById("liquid").style.height = `${liquidHeight}px`;
}

async function increaseQuantity() {
    try {
        const currentQuantity = parseInt(document.getElementById("quantity").innerText);
        const quantityInput = parseInt(document.getElementById("quantity-input").value);
        const maxAmount = parseInt(document.getElementById("quantity-input").max);
        const newQuantity = currentQuantity + quantityInput;
        if (newQuantity <= maxAmount) {
            const response = await fetch("/jars/adjustQuantity", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ id: 1, quantity: newQuantity }) // Assuming jar ID is 1
            });
            if (!response.ok) {
                throw new Error("Failed to adjust jar quantity");
            }
            document.getElementById("quantity").innerText = newQuantity;
            if (newQuantity < 200) {
                document.getElementById("notification").style.display = "block";
                
                await notifyBackend();
            }
        } else {
            console.error("Exceeds maximum amount");
        }
    } catch (error) {
        console.error(error);
    }
}

async function decreaseQuantity() {
    try {
        const currentQuantity = parseInt(document.getElementById("quantity").innerText);
        const quantityInput = parseInt(document.getElementById("quantity-input").value);
        const newQuantity = currentQuantity - quantityInput;
        if (newQuantity >= 0) {
            const response = await fetch("/jars/adjustQuantity", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ id: 1, quantity: newQuantity }) // Assuming jar ID is 1
            });
            if (!response.ok) {
                throw new Error("Failed to adjust jar quantity");
            }
            document.getElementById("quantity").innerText = newQuantity;
            if (newQuantity < 200) {
                document.getElementById("notification").style.display = "block";
                // Send notification to backend
                await notifyBackend();
            }
        } else {
            console.error("Quantity cannot be negative");
        }
    } catch (error) {
        console.error(error);
    }
}

async function notifyBackend() {
    try {
        const response = await fetch("/jars/notify", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ id: 1}) // Assuming jar ID is 1
        });
        if (!response.ok) {
            throw new Error("Failed to send notification to backend");
        }
        console.log("Notification sent to backend");
    } catch (error) {
        console.error(error);
    }
}

window.onload = function() {
    fetchJarInfo();
    setInterval(fetchJarInfo, 500);
};

