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
  return { add: add };
})();
