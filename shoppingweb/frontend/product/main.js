$(document).ready(() => {
  
    var products = [
        // 您的商品數據
    ];
    const fetchProducts = () => {
       // $.get('https://fakestoreapi.com/products/',result);       
       $.get('http://localhost:8080/products',result);       
    };
    
    const result=(data)=>{
       products=data;
       displayProducts(currentPage);
       generatePagination();
    };
  
    const productsPerPage = 9;
    let currentPage = 1;
  
    function displayProducts(page) {      
      const startIndex = (page - 1) * productsPerPage;
      const endIndex = startIndex + productsPerPage;
  
      $('#productsContainer').empty();
  
      for (let i = startIndex; i < endIndex && i < products.length; i++) {
        const product = products[i];
        $('#productsContainer').append(`
        <div class="col-md-4 p-3" style="">
          <div class="card box-shadow" style="width:90%">
            <img class="card-img-top" src="http://localhost:8080/product/${product.productid}.jpg">
            <div class="card-body">
              <h5 class="card-title">${product.pname}</h5>                
               <p class="card-text">$${product.unitprice}</p>
              <p class="card-text">${product.ptext}</p>
              <div class="d-flex justify-content-between align-items-center">
                <div class="btn-group">
                <button  type="button" class="btn btn-sm btn-outline-secondary" data-product-id="${product.productid}" 
                data-toggle="modal" onclick="addcart('${product.productid}')">
                  <i class="fa fa-fw fa-cart-plus fa-lg"></i><i class="fa fa-fwfa-cart-plus"></i></button>
                </div> <small class="text-muted" contenteditable="true"></small>
              </div>
            </div>
          </div>
        </div>         
        `);
        
      }
   
    }

    function generatePagination() {
      $('#productPagination').empty();
  
      const totalPages = Math.ceil(products.length / productsPerPage);
  
      for (let i = 1; i <= totalPages; i++) {
        const pageItem = $(`
          <li class="page-item ${i === currentPage ? 'active' : ''}">
            <a class="page-link" href="#" data-page="${i}">${i}</a>
          </li>
        `);
  
        pageItem.on('click', (e) => {
          e.preventDefault();
          currentPage = parseInt($(e.target).data('page'));
          displayProducts(currentPage);
          generatePagination();
        });
  
        $('#productPagination').append(pageItem);
      }
    }
   
    fetchProducts();
   
  });
  