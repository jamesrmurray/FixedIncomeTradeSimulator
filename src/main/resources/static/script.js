window.addEventListener('DOMContentLoaded', () => {
    fetch('/api/trades') // Replace with your API endpoint for fetching trade data
        .then(response => response.json())
        .then(data => {
            const tableBody = document.querySelector('#trade-table');
            data.forEach(trade => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${trade.bondType}</td>
                    <td>${trade.quantity}</td>
                    <td>${trade.price}</td>
                    <td>${trade.direction}</td>
                `;
                tableBody.appendChild(row);
            });
        })
        .catch(error => console.error(error));
});

