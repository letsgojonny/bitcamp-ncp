showInput();
getMembers();

const html = document.querySelector("#tr-template").innerHTML;
const templateEngine = Handlebars.compile(html);

function showInput() {
  let el = document.querySelectorAll(".input");
  for (let e of el) {
    e.classList.remove("invisible");
  }

  el = document.querySelectorAll(".edit");
  for (let e of el) {
    e.classList.add("invisible");
  }
}

function showEdit() {
  let el = document.querySelectorAll(".input");
  for (let e of el) {
    e.classList.add("invisible");
  }

  el = document.querySelectorAll(".edit");
  for (let e of el) {
    e.classList.remove("invisible");
  }
}

document.querySelector("input[name='keyword']").onkeyup = (e) => {
  getMembers(e.target.value);
};

document.querySelector("#btn-search").onclick = () => {
  getMembers(keyword);
};

function getMembers(keyword) {
  let qs = "";
  if (keyword) {
    qs = `?keyword=${keyword}`;
  }

  fetch("../members" + qs)
    .then((response) => {
      return response.json();
    })
    .then((result) => {
      document.querySelector("#member-table > tbody").innerHTML =
        templateEngine(result.data);
    });
}

function getMember(e) {
  let no = e.currentTarget.getAttribute("data-no");

  fetch("../members/" + no)
    .then((response) => {
      return response.json();
    })
    .then((result) => {
      if (result.status == "failure") {
        alert("멤버을 조회할 수 없습니다.");
        return;
      }

      let member = result.data;
      console.log(member);
      document.querySelector("#f-no").value = member.no;
      document.querySelector("#f-name").value = member.name;
      document.querySelector("#f-tel").value = member.tel;
      document.querySelector("#f-email").value = member.email;
      document.querySelector("#f-id").value = member.id;
      document.querySelector("#f-profilePhoto").value = member.profilePhoto;
      document.querySelector("#f-nickname").value = member.nickname;
      document.querySelector("#f-postNo").value = member.postNo;
      document.querySelector("#f-basicAddress").value = member.basicAddress;
      document.querySelector("#f-detailAddress").value = member.detailAddress;
      document.querySelector("#f-birth").value = member.birth;
      document.querySelector("#f-statusMessage").value = member.statusMessage;
      document.querySelector("#f-createdDate").innerHTML = member.createdDate;
      document.querySelector("#f-authority").value = member.authority;
      showEdit();
    });
}

document.querySelector("#btn-insert").onclick = () => {
  const form = document.querySelector("#member-form");
  const formData = new FormData(form);

  let json = JSON.stringify(Object.fromEntries(formData));

  fetch("../members", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: json,
  })
    .then((response) => {
      return response.json();
    })
    .then((result) => {
      if (result.status == "success") {
        location.reload();
      } else {
        alert("입력 실패!");
        console.log(result.data);
      }
    })
    .catch((exception) => {
      alert("입력 중 오류 발생!");
      console.log(exception);
    });
};

document.querySelector("#btn-update").onclick = () => {
  const form = document.querySelector("#member-form");
  const formData = new FormData(form);

  // FormData ==> Query String
  // 방법1)
  //let qs = [...formData.entries()].map(x => `${encodeURIComponent(x[0])}=${encodeURIComponent(x[1])}`).join('&');
  // 방법2)
  //let qs = new URLSearchParams(formData).toString();
  //console.log(qs);

  let json = JSON.stringify(Object.fromEntries(formData));
  //console.log(json);

  fetch("../members/" + document.querySelector("#f-no").value, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
      //"Content-Type": "application/x-www-form-urlencoded"
    },
    //body: formData
    body: json,
    //body: qs
  })
    .then((response) => {
      return response.json();
    })
    .then((result) => {
      if (result.status == "success") {
        alert("변경 했습니다.");
        location.reload();
      } else {
        alert("변경 실패!");
        console.log(result.data);
      }
    })
    .catch((exception) => {
      alert("변경 중 오류 발생!");
      console.log(exception);
    });
};

document.querySelector("#btn-delete").onclick = () => {
  fetch("../members/" + document.querySelector("#f-no").value, {
    method: "DELETE",
  })
    .then((response) => {
      return response.json();
    })
    .then((result) => {
      if (result.status == "success") {
        location.reload();
      } else {
        alert("멤버 삭제 실패!");
      }
    })
    .catch((exception) => {
      alert("멤버 삭제 중 오류 발생!");
      console.log(exception);
    });
};

document.querySelector("#btn-cancel").onclick = () => {
  showInput();
};

// entries ==> query string
function toQueryStringFromEntries(entries) {
  let qs = "";
  for (let [key, value] of entries) {
    if (qs.length > 0) {
      qs += "&";
    }
    qs += encodeURIComponent(key) + "=" + encodeURIComponent(value);
  }
  return qs;
}

function toQueryStringFromEntries2(entries) {
  let arr = [];
  for (let entry of entries) {
    arr.push(entry);
  }

  //console.log(arr);

  let arr2 = arr.map(
    (x) => `${encodeURIComponent(x[0])}=${encodeURIComponent(x[1])}`
  );
  //console.log(arr2);

  let str = arr2.join("&");
  //console.log(str);

  return str;
}

function toQueryStringFromEntries3(entries) {
  let arr = [...entries];

  //console.log(arr);

  let arr2 = arr.map(
    (x) => `${encodeURIComponent(x[0])}=${encodeURIComponent(x[1])}`
  );
  //console.log(arr2);

  let str = arr2.join("&");
  //console.log(str);

  return str;
}
