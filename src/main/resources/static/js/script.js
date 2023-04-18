// Send request to server to classify text
function classify_text() {
    var text = document.getElementById("text").value;
  
    if (text != "") {
      var btn = document.getElementById("classify_btn");
      btn.classList.add("button--loading");
      btn.textContent = ''
      $.ajax({
        url: "sentiment", // point to server-side URL
        dataType: "text", // what to expect back from server
        cache: false,
        contentType: false,
        processData: false,
        data: "text="+text,
        type: "get",
        success: function (response) {
          console.log(response)
          document.getElementById("result").innerText = response;
          document.getElementById("classify_btn").disabled = false; // activate classify button
        },
        error: function (response) {
          alert(response, "danger");
        },
        complete: function (response) {
          btn.classList.remove("button--loading")
          btn.textContent = 'Classify text'
        }
      });
    } else {
      alert("Please enter some text!", "danger");
    }
  }
    
  // Bootstrap alert
  const alert = (message, type) => {
    const alertPlaceholder = document.getElementById('alerts')
    const wrapper = document.createElement('div')
    wrapper.innerHTML = [
      `<div class="alert alert-${type} alert-dismissible fade show" role="alert">`,
      `   <div>${message}</div>`,
      '   <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>',
      '</div>'
    ].join('')
  
    alertPlaceholder.append(wrapper)
  }