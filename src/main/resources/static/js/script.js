let quantities = []; // Array to store quantities for all rounds
const socket = new SockJS('/ws');
const stompClient = Stomp.over(socket);
stompClient.debug = () => {}
console.log("version 1.2.6");
stompClient.connect({}, function (frame) {
    stompClient.subscribe('/topic/weight', function (message) {
        const currentWeight = JSON.parse(message.body);
        // Update the UI with the current weight
        updateWeight(currentWeight);
    });

    stompClient.subscribe('/topic/round', function (message) {
        const roundInfo = JSON.parse(message.body);
        console.log("Received round info:", roundInfo);
        
        // Append the round information to the table
        const roundTableBody = document.getElementById("round-table-body");
        const newRow = roundTableBody.insertRow();
        newRow.innerHTML = `
            <td>${roundInfo.roundId}</td>
            <td>${roundInfo.startingWeight}</td>
            <td>${roundInfo.removedWeight}</td>
            <td>${roundInfo.weightChangeDueToRemoval}</td>
            <td>${roundInfo.refillAmount}</td>
            <td>${roundInfo.endingWeight}</td>
        `;
    });
});


document.addEventListener("DOMContentLoaded", function() { 
    // Event listener for execute button
    document.getElementById("execute-btn").addEventListener("click", function() {
        const inputQuantity = document.getElementById("input-quantity").value;
        console.log("input: ", inputQuantity);
        addQuantities(inputQuantity); // Call addQuantities with the input value
        
        executeRefill(); // Start the refill process for the first round
    }); 
    
});

function updateWeight(currentWeight) {
    const jar = document.getElementById("jar");
    const quantityDisplay = document.getElementById("quantity");
    const liquid = document.getElementById("liquid");

    quantityDisplay.innerText = currentWeight;

    // Calculate the liquid level based on the current weight and maximum capacity
    const maxCapacity = 1000; // Example maximum capacity, adjust as needed
    const liquidLevel = (currentWeight / maxCapacity) * 100;

    // Update the liquid level CSS
    liquid.style.height = `${liquidLevel}%`;
}


function addQuantities(inputQuantity) {
    quantities = inputQuantity.split(",").map(Number);
}


// Function to execute refill for one round
function executeRefill() {

    var quantityForRound = 0;
    if (quantities.length === 0) {
        console.log("No more rounds for input");
    } else {
        quantityForRound = quantities.shift();
    }

    // AJAX request to backend to refill jar for one round
    fetch('/refill', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(quantityForRound), // Send quantity for one round
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        setTimeout(executeRefill, 1000);
        console.log("next");
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}
