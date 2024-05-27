$(document).ready(start);

function start(){
    getfunctionlist();
    getproducts();

  }
  
  var identity = 'guest';

  function tomemberdata()
  {
    $.get("./member/UpdateByMemberSelf.html",setmainspace); 
  }

  function tosalesorderdata()
  {
    $.get("./salesorder/salesManage.html",setmainspace); 
  }


  function setmainspace(data)
  {
    //alert("run");
    $("#mainspace").empty();
    $("#mainspace").append(data);
  }

  function setmainspace2(data)
  {
    //alert("run");
    $("#mainspace2").empty();
    $("#mainspace2").append(data);
  }

  function touserdata()
  {
    var dep = sessionStorage.getItem("loginUserDep");
    //alert(dep);
    
    if(dep==="M")
    {
      $.get("./user/manager/userquery_mgr.html",setmainspace);  
    }
    else
    {
      $.get("./user/employee/userupdate_emp.html",setmainspace); 
    }
  }

  function tocartpage()
  {
    if(identity !== 'member'){
      alert("請先登入再挑選商品");
      memberlogin();
    }else
    $.get("./salesorder/cart.html",setmainspace);
    $("#cartModal").hide();
 }


  function toproductdata()
  {
    $.get("./product/productquery.html",setmainspace);  
  }

  function toproducttypedata()
  {
    $.get("./producttype/producttypequery.html",setmainspace);  
  }

  function tohistoryorders()
  {
    $.get("./salesorder/querySalesById.html",setmainspace); 
  }

  function memberlogout()
  {
    location.href = "http://127.0.0.1:5500/shoppingweb/index.html";
    sessionStorage.clear();      
  }

  function userlogout()
  {
    location.href = "http://127.0.0.1:5500/shoppingweb/index.html";  
    sessionStorage.clear();
  }

  function memberlogin()
  {
    $.get("./member/login.html",setmainspace);

  }

  function userlogin()
  {
    $.get("./user/login.html",setmainspace);

  }
  
  function fl(data)
  {      
    $("#functionlist").append(data);     
  }

  function getfunctionlist()
  {
    //alert("run");
    $.get("function_list_guess.html",fl);      
  }

  function getproducts()
  {
    //alert("run");
    $.get("./product/products.html",setmainspace);      
  }

  
  function getfunctionlist_member()
  {    
    if(identity === 'member')
    {
        document.getElementById('memberlist').style.display="";
        document.getElementById('memberlogin').style.display="none";
        document.getElementById('userlogin').style.display="none";
        document.getElementById('memberlogout').style.display="";
        
    } 
  }


  function getfunctionlist_user()
  {
    if(identity === 'user')
    {
        //alert("success");
        // $("#userlist").show();
        // $("#memberlogin").hide();
        // $("#userlogin").hide();
        // $("#cartbtn").hide();
        // $("#cartbtni").hide();
        // $("#userlogout").show();
        
        document.getElementById('userlist').style.display="";
        document.getElementById('memberlogin').style.display="none";
        document.getElementById('userlogin').style.display="none";
        document.getElementById('cartbtn').style.display="none";
        document.getElementById('cartbtni').style.display="none";
        document.getElementById('userlogout').style.display="";
        // alert("success");
    }
  }

  function getIndex()
  {
    if(identity == 'member')
    {          
      getfunctionlist_member();
      getproducts();
    }
    else if(identity == 'user')
    {           
      getfunctionlist_user();
      getproducts();
    }
    else
    {
      location.href = "http://127.0.0.1:5500/shoppingweb/index.html";
    }
  }