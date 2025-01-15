const BASE_URL = "http://localhost:8080/api"; // Replace with your Spring Boot base URL

// Login function
async function login() {
    const username = document.getElementById("loginUsername").value;
    const password = document.getElementById("loginPassword").value;
    const messageElement = document.getElementById("loginMessage");

    const requestBody = {
        username: username,
        password: password,
    };

    try {
        const response = await fetch(`${BASE_URL}/login`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(requestBody),
        });

        if (response.ok) {
            const result = await response.json();
            messageElement.innerText = "Login successful!";
            messageElement.classList.remove("error");
            messageElement.classList.add("message");
            console.log("Login Response:", result);
        } else {
            const error = await response.json();
            messageElement.innerText = `Login failed: ${error.message || "Invalid credentials"}`;
            messageElement.classList.remove("message");
            messageElement.classList.add("error");
        }
    } catch (err) {
        messageElement.innerText = `Error: ${err.message}`;
        messageElement.classList.remove("message");
        messageElement.classList.add("error");
    }
}

// Register function
async function register() {
    const username = document.getElementById("registerUsername").value;
    const password = document.getElementById("registerPassword").value;
    const messageElement = document.getElementById("registerMessage");

    const requestBody = {
        username: username,
        password: password,
    };

    try {
        const response = await fetch(`${BASE_URL}/register`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(requestBody),
        });

        if (response.ok) {
            const result = await response.json();
            messageElement.innerText = "Registration successful!";
            messageElement.classList.remove("error");
            messageElement.classList.add("message");
            console.log("Register Response:", result);
        } else {
            const error = await response.json();
            messageElement.innerText = `Registration failed: ${error.message || "Error occurred"}`;
            messageElement.classList.remove("message");
            messageElement.classList.add("error");
        }
    } catch (err) {
        messageElement.innerText = `Error: ${err.message}`;
        messageElement.classList.remove("message");
        messageElement.classList.add("error");
    }
}
