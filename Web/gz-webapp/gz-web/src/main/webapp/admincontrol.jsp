<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Admin Panel</title>
        <link rel="stylesheet" href="src/stylesadmin.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="navbar.jsp" />
        
        <div class="container-fluid">
            <div class="admin-header">
                <h2>Panel de Administrador</h2>
                <p>Gestiona usuarios, productos, y plantas</p>
            </div>
            
            <!-- Users Section -->
            <div class="table-header">
            <h3 class="section-title">Users Management</h3>
            <div class="table-controls">
                <div class="search-container">
                    <input type="text" class="search-input" placeholder="Search users..." data-table="usersTable">
                    <button class="btn btn-outline-secondary clear-search" type="button">
                        <i class="bi bi-x"></i>
                    </button>
                </div>
                <div class="dropdown">
                    <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown">
                        <i class="bi bi-funnel"></i> Filter
                    </button>
                    <div class="dropdown-menu">
                        <div class="filter-option">
                            <label class="form-label">Admin Access</label>
                            <select class="form-select" data-filter="adminAccess">
                                <option value="">All</option>
                                <option value="yes">Yes</option>
                                <option value="no">No</option>
                            </select>
                        </div>
                        <div class="filter-option">
                            <label class="form-label">Location</label>
                            <select class="form-select" data-filter="location">
                                <option value="">All</option>
                                <option value="Mexico City">Mexico City</option>
                                <option value="New York">New York</option>
                                <option value="Tokyo">Tokyo</option>
                            </select>
                        </div>
                        <div class="mt-2">
                            <button class="btn btn-primary btn-sm w-100 apply-filters">Apply Filters</button>
                        </div>
                    </div>
                </div>
            </div>
            </div>
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Nombre de Usuario</th>
                            <th>Email</th>
                            <th>Edad</th>
                            <th>Ubicacion</th>
                            <th>Admin.</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody id="usersTable">
                        <tr data-id="1">
                            <td>carlos_martinez</td>
                            <td>carlos.martinez@gmail.com</td>
                            <td>25</td>
                            <td>Mexico City</td>
                            <td>Yes</td>
                            <td class="edit-buttons">
                                <button class="btn btn-primary btn-action edit-btn"><i class="bi bi-pencil"></i></button>
                                <button class="btn btn-danger btn-action delete-btn"><i class="bi bi-trash"></i></button>
                            </td>
                        </tr>
                        <!-- More rows will be populated here -->
                    </tbody>
                </table>
            </div>
            
            <!-- Products Section -->
            <div class="table-header">
            <h3 class="section-title">Products Management</h3>
            <div class="table-controls">
                <div class="search-container">
                    <input type="text" class="search-input" placeholder="Search products..." data-table="productsTable">
                    <button class="btn btn-outline-secondary clear-search" type="button">
                        <i class="bi bi-x"></i>
                    </button>
                </div>
                <div class="dropdown">
                    <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown">
                        <i class="bi bi-funnel"></i> Filter
                    </button>
                    <div class="dropdown-menu">
                        <div class="filter-option">
                            <label class="form-label">Price Range</label>
                            <select class="form-select" data-filter="priceRange">
                                <option value="">All</option>
                                <option value="0-50">$0 - $50</option>
                                <option value="51-100">$51 - $100</option>
                                <option value="100+">$100+</option>
                            </select>
                        </div>
                        <div class="filter-option">
                            <label class="form-label">Stock Status</label>
                            <select class="form-select" data-filter="stock">
                                <option value="">All</option>
                                <option value="in">In Stock</option>
                                <option value="low">Low Stock</option>
                                <option value="out">Out of Stock</option>
                            </select>
                        </div>
                        <div class="mt-2">
                            <button class="btn btn-primary btn-sm w-100 apply-filters">Apply Filters</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Descripcion</th>
                            <th>Precio</th>
                            <th>Cantidad</th>
                            <th>ID Planta</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody id="productsTable">
                        <tr data-id="1">
                            <td>Aloe Vera Gel</td>
                            <td>Gel for skin care</td>
                            <td>50.00</td>
                            <td>5</td>
                            <td>1</td>
                            <td class="edit-buttons">
                                <button class="btn btn-primary btn-action edit-btn"><i class="bi bi-pencil"></i></button>
                                <button class="btn btn-danger btn-action delete-btn"><i class="bi bi-trash"></i></button>
                            </td>
                        </tr>
                        <!-- More rows will be populated here -->
                    </tbody>
                </table>
            </div>
            
            <!-- Plants Section -->
            <div class="table-header">
            <h3 class="section-title">Plants Management</h3>
            <div class="table-controls">
                <div class="search-container">
                    <input type="text" class="search-input" placeholder="Search plants..." data-table="plantsTable">
                    <button class="btn btn-outline-secondary clear-search" type="button">
                        <i class="bi bi-x"></i>
                    </button>
                </div>
                <div class="dropdown">
                    <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown">
                        <i class="bi bi-funnel"></i> Filter
                    </button>
                    <div class="dropdown-menu">
                        <div class="filter-option">
                            <label class="form-label">Quantity</label>
                            <select class="form-select" data-filter="quantity">
                                <option value="">All</option>
                                <option value="1-5">1-5</option>
                                <option value="6-10">6-10</option>
                                <option value="10+">10+</option>
                            </select>
                        </div>
                        <div class="filter-option">
                            <label class="form-label">Planting Date</label>
                            <select class="form-select" data-filter="plantingDate">
                                <option value="">All</option>
                                <option value="last-month">Last Month</option>
                                <option value="last-6months">Last 6 Months</option>
                                <option value="last-year">Last Year</option>
                            </select>
                        </div>
                        <div class="mt-2">
                            <button class="btn btn-primary btn-sm w-100 apply-filters">Apply Filters</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Descripcion</th>
                            <th>Cantidad</th>
                            <th>Fecha de Plantado</th>
                            <th>ID Usuario</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody id="plantsTable">
                        <tr data-id="1">
                            <td>Aloe Vera</td>
                            <td>Medicinal plant for skin</td>
                            <td>5</td>
                            <td>2023-01-15</td>
                            <td>1</td>
                            <td class="edit-buttons">
                                <button class="btn btn-primary btn-action edit-btn"><i class="bi bi-pencil"></i></button>
                                <button class="btn btn-danger btn-action delete-btn"><i class="bi bi-trash"></i></button>
                            </td>
                        </tr>
                        <!-- More rows will be populated here -->
                    </tbody>
                </table>
            </div>
        </div>
        
        <div class="modal fade" id="deleteConfirmModal" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Eliminar Articulo</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        ¿Estás seguro de que quieres eliminar este registro?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                        <button type="button" class="btn btn-danger" onclick="removeItem()" id="confirmDelete">Eliminar</button>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <script>
            document.addEventListener('DOMContentLoaded', function() {
                // Add click handlers for all edit buttons
                document.querySelectorAll('.edit-btn').forEach(btn => {
                    btn.addEventListener('click', function(e) {
                        const row = e.target.closest('tr');
                        toggleEditMode(row);
                    });
                });
                
                // Add click handlers for all delete buttons
                let rowToDelete = null; // Keep track of the row to delete

                document.querySelectorAll('.delete-btn').forEach(btn => {
                    btn.addEventListener('click', function(e) {
                        // Store the row to delete
                        rowToDelete = e.target.closest('tr');

                        // Show the modal
                        const modal = new bootstrap.Modal(document.getElementById('deleteConfirmModal'));
                        modal.show();
                    });
                });

                function removeItem() {
                    if (rowToDelete) {
                        rowToDelete.remove(); // Remove the row from the table
                        rowToDelete = null; // Reset the row reference

                        // Close the modal
                        const modal = bootstrap.Modal.getInstance(document.getElementById('deleteConfirmModal'));
                        modal.hide();

                        // Optionally, add an AJAX call to delete the item from the database here
                        console.log('Item deleted');
                    }
                }

                
                function toggleEditMode(row) {
                    const cells = row.getElementsByTagName('td');
                    const isEditing = row.classList.contains('edit-mode');
                    
                    if (!isEditing) {
                        // Convert to edit mode
                        row.classList.add('edit-mode');
                        Array.from(cells).forEach((cell, index) => {
                            if (index < cells.length - 1) { // Skip the actions column
                                const value = cell.textContent;
                                cell.innerHTML = `<input type="text" value="${value}">`;
                            } else {
                                // Replace edit button with save button
                                const editBtn = cell.querySelector('.edit-btn');
                                editBtn.innerHTML = '<i class="bi bi-check-lg"></i>';
                                editBtn.classList.remove('btn-primary');
                                editBtn.classList.add('btn-success');
                            }
                        });
                    } else {
                        // Save changes
                        row.classList.remove('edit-mode');
                        Array.from(cells).forEach((cell, index) => {
                            if (index < cells.length - 1) { // Skip the actions column
                                const input = cell.querySelector('input');
                                cell.textContent = input.value;
                            } else {
                                // Restore edit button
                                const saveBtn = cell.querySelector('.edit-btn');
                                saveBtn.innerHTML = '<i class="bi bi-pencil"></i>';
                                saveBtn.classList.remove('btn-success');
                                saveBtn.classList.add('btn-primary');
                            }
                        });
                    }
                }
            });
            document.addEventListener('DOMContentLoaded', function() {
            // Search functionality
            document.querySelectorAll('.search-input').forEach(input => {
                input.addEventListener('input', function() {
                    const searchTerm = this.value.toLowerCase();
                    const tableId = this.dataset.table;
                    const rows = document.querySelectorAll(`#${tableId} tr:not(.no-results)`);

                    let hasResults = false;

                    rows.forEach(row => {
                        const text = row.textContent.toLowerCase();
                        if (text.includes(searchTerm)) {
                            row.style.display = '';
                            hasResults = true;
                        } else {
                            row.style.display = 'none';
                        }
                    });

                    // Show/hide no results message
                    let noResultsRow = document.querySelector(`#${tableId} tr.no-results`);
                    if (!hasResults) {
                        if (!noResultsRow) {
                            noResultsRow = document.createElement('tr');
                            noResultsRow.className = 'no-results';
                            const td = document.createElement('td');
                            td.colSpan = rows[0].cells.length;
                            td.textContent = 'No results found';
                            td.className = 'text-center py-3';
                            noResultsRow.appendChild(td);
                            document.querySelector(`#${tableId}`).appendChild(noResultsRow);
                        }
                        noResultsRow.style.display = '';
                    } else if (noResultsRow) {
                        noResultsRow.style.display = 'none';
                    }
                });
            });

            // Clear search
            document.querySelectorAll('.clear-search').forEach(button => {
                button.addEventListener('click', function() {
                    const input = this.previousElementSibling;
                    input.value = '';
                    input.dispatchEvent(new Event('input'));
                });
            });

            // Filter functionality
            document.querySelectorAll('.apply-filters').forEach(button => {
                button.addEventListener('click', function() {
                    const dropdown = this.closest('.dropdown-menu');
                    const filters = dropdown.querySelectorAll('[data-filter]');
                    const activeFilters = [];

                    filters.forEach(filter => {
                        if (filter.value) {
                            activeFilters.push({
                                type: filter.dataset.filter,
                                value: filter.value
                            });
                        }
                    });

                    // Apply filters to table
                    // This is a simplified example - you'll need to adjust the filtering logic
                    // based on your specific requirements
                    applyFilters(activeFilters, dropdown);
                });
            });

            function applyFilters(filters, dropdown) {
                const table = dropdown.closest('.table-responsive').querySelector('table');
                const rows = table.querySelectorAll('tbody tr');

                rows.forEach(row => {
                    let showRow = true;

                    filters.forEach(filter => {
                        // Add your filter logic here based on filter.type and filter.value
                        // This is just an example:
                        const cellValue = row.querySelector(`td[data-${filter.type}]`)?.textContent.toLowerCase();
                        if (cellValue && !cellValue.includes(filter.value.toLowerCase())) {
                            showRow = false;
                        }
                    });

                    row.style.display = showRow ? '' : 'none';
                });

                // Close dropdown
                bootstrap.Dropdown.getInstance(dropdown.previousElementSibling).hide();
            }
        });
        </script>
    </body>
</html>