document.addEventListener('DOMContentLoaded', function() {
    // Check if we're on the form page
    if (document.getElementById('productForm')) {
        const productForm = document.getElementById('productForm');
        const clearBtn = document.getElementById('clearBtn');
        
        // Handle form submission
        productForm.addEventListener('submit', function(e) {
            e.preventDefault();
            
            const productId = document.getElementById('productId').value;
            const name = document.getElementById('name').value;
            const description = document.getElementById('description').value;
            const price = parseFloat(document.getElementById('price').value);
            
            const product = {
                name: name,
                description: description,
                price: price
            };
            
            const url = productId ? `/products/${productId}` : '/products';
            const method = productId ? 'PUT' : 'POST';
            
            fetch(url, {
                method: method,
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(product)
            })
            .then(response => response.text())
            .then(data => {
                alert(data);
                if (!productId) {
                    productForm.reset();
                }
            })
            .catch(error => console.error('Error:', error));
        });
        
        // Handle clear button
        clearBtn.addEventListener('click', function() {
            productForm.reset();
            document.getElementById('productId').value = '';
        });
        
        // Check for URL parameters (for editing)
        const urlParams = new URLSearchParams(window.location.search);
        const editId = urlParams.get('edit');
        
        if (editId) {
            fetch(`/products/${editId}`)
                .then(response => response.json())
                .then(product => {
                    document.getElementById('productId').value = product.id;
                    document.getElementById('name').value = product.name;
                    document.getElementById('description').value = product.description;
                    document.getElementById('price').value = product.price;
                })
                .catch(error => console.error('Error:', error));
        }
    }
    
    // Check if we're on the list page
    if (document.getElementById('productsTable')) {
        fetch('/products')
            .then(response => response.json())
            .then(products => {
                const tbody = document.querySelector('#productsTable tbody');
                tbody.innerHTML = '';
                
                products.forEach(product => {
                    const row = document.createElement('tr');
                    
                    row.innerHTML = `
                        <td>${product.id}</td>
                        <td>${product.name}</td>
                        <td>${product.description}</td>
                        <td>R$ ${product.price.toFixed(2)}</td>
                        <td>
                            <a href="/form.html?edit=${product.id}">Editar</a>
                            <button onclick="deleteProduct(${product.id})">Excluir</button>
                        </td>
                    `;
                    
                    tbody.appendChild(row);
                });
            })
            .catch(error => console.error('Error:', error));
    }
});

// Global function for delete
function deleteProduct(id) {
    if (confirm('Tem certeza que deseja excluir este produto?')) {
        fetch(`/products/${id}`, {
            method: 'DELETE'
        })
        .then(response => response.text())
        .then(data => {
            alert(data);
            window.location.reload();
        })
        .catch(error => console.error('Error:', error));
    }
}