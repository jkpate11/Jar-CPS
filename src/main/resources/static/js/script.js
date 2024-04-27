let quantities = []; 
const socket = new SockJS('/ws');
const stompClient = Stomp.over(socket);
stompClient.debug = () => {}
console.log("version 1.2.6");
stompClient.connect({}, function (frame) {
    stompClient.subscribe('/topic/weight', function (message) {
        const currentWeight = JSON.parse(message.body);

        updateWeight(currentWeight);
    });

    stompClient.subscribe('/topic/round', function (message) {
        const roundInfo = JSON.parse(message.body);
        console.log("Received round info:", roundInfo);

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

    document.getElementById("execute-btn").addEventListener("click", function() {
        const inputQuantity = document.getElementById("input-quantity").value;
        console.log("input: ", inputQuantity);
        addQuantities(inputQuantity); 
        
        executeRefill(); 
    }); 

    document.getElementById("send-file-btn").addEventListener("click", function() {
        const selectedFile = "src/main/resources/test1.csv"; 
        if (selectedFile) {
            const fileName = selectedFile; 
            console.log("Selected file:", fileName);
            sendFileName(fileName);
        } else {
            console.log("No file selected.");
        }
    }); 

    document.getElementById("fail-btn").addEventListener("click", function() {
        addWeight();
    }); 

    
});

function sendFileName(fileName) {
    fetch('/dataFromFile', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: fileName, 
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {

        console.log("Done");
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}

function addWeight() {
    fetch('/addExtraWeight', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(1200), 
    })
    .then(response => {
        if (response.ok) {
            alert("Due to overflow, the system has been stopped. An email has been sent to the user."); 
        }
        return response.json();
    })
    .then(data => {
        console.log("Failed done");
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}

function updateWeight(currentWeight) {
    const jar = document.getElementById("jar");
    const quantityDisplay = document.getElementById("quantity");
    const liquid = document.getElementById("liquid");

    quantityDisplay.innerText = currentWeight;


    const maxCapacity = 1000;
    const liquidLevel = (currentWeight / maxCapacity) * 100;


    liquid.style.height = `${liquidLevel}%`;
}


function addQuantities(inputQuantity) {
    quantities = inputQuantity.split(",").map(Number);
}


function executeRefill() {

    var quantityForRound = 0;
    if (quantities.length === 0) {
        console.log("No more rounds for input");
    } else {
        quantityForRound = quantities.shift();
    }

    fetch('/refill', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(quantityForRound), 
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
