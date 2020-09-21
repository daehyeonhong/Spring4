/**
 * reply.js
 */

console.log("Reply Module");

let replyService = (function () {
  function add(reply, callback) {
    console.log("reply.....");

    $.ajax({
      type: "post",
      url: "/replies/new",
      data: JSON.stringify(reply),
      contentType: "application/json; charset=UTF-8",
      success: function (result, status, xhr) {
        if (callback) {
          callback(result);
        }
      },
      error: function (xhr, status, er) {
        if (error) {
          error(er);
        }
      },
    });
  }

  function getList(parameter, callback, error) {
    let bno = parameter.bno;
    let page = parameter.page || 1;

    $.getJSON("/replies/page/" + bno + "/" + page + ".json", function (data) {
      if (callback) {
        //callback(data);
        callback(
          data.replyCount,
          data.list
        ); /* 댓글 숫자와 목록을 가져오는 경우 */
      }
    }).fail(function (xhr, status, err) {
      if (error) {
        error();
      }
    });
  }

  function remove(rno, callback, error) {
    $.ajax({
      type: "delete",
      url: "/replies/" + rno,
      success: function (deleteResult, status, xhr) {
        if (callback) {
          callback(deleteResult);
        }
      },
      error: function (xhr, status, er) {
        if (error) {
          error(er);
        }
      },
    });
  }

  function update(reply, callback, error) {
    $.ajax({
      type: "put",
      url: "/replies/" + reply.rno,
      data: JSON.stringify(reply),
      contentType: "application/json; charset=UTF-8",
      success: function (result, status, xhr) {
        if (callback) {
          callback(result);
        }
      },
      error: function (xhr, status, er) {
        if (error) {
          error(er);
        }
      },
    });
  }

  function get(rno, callback, error) {
    $.get("/replies/" + rno + ".json", function (result) {
      if (callback) {
        callback(result);
      }
    }).fail(function (xhr, status, err) {
      if (error) {
        error();
      }
    });
  }

  function displayTime(timeValue) {
    let today = new Date();
    let gap = today.getTime() - timeValue;

    console.log(gap, gap < 1000 * 60 * 60 * 24);

    let dateObjet = new Date(timeValue);

    if (gap < 1000 * 60 * 60 * 24) {
      let hh = dateObjet.getHours();
      let mi = dateObjet.getMinutes();
      let ss = dateObjet.getSeconds();
      return [
        (hh > 9 ? "" : "0") + hh,
        ":",
        (mi > 9 ? "" : "0") + mi,
        ":",
        (ss > 9 ? "" : "0") + ss,
      ].join("");
    } else {
      let yy = dateObjet.getFullYear();
      let mm = dateObjet.getMonth() + 1;
      let dd = dateObjet.getDate();
      return [
        yy,
        "/",
        (mm > 9 ? "" : "0") + mm,
        "/",
        (dd > 9 ? "" : "0") + dd,
      ].join("");
    }
  }

  return {
    add: add,
    getList: getList,
    remove: remove,
    update: update,
    get: get,
    displayTime: displayTime,
  };
})(); /* 즉시 실행 */
