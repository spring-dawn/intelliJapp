 'use strict';

    //상세
    cancelBtn.addEventListener('click',e=>{
      const url = `/bbs/${bbsId.value}`;
      location.href = url;
    });

//    //저장. POST.
//    saveBtn.addEventListener('click',e=>{
//      const url = `/bbs/${bbsId.value}/edit`;
//      location.href = url;
//    });

    //목록
    listBtn.addEventListener('click',e=>{
      location.href="/bbs";
    });
