import { Injectable } from '@angular/core';
import Quill from 'quill';

@Injectable({
  providedIn: 'root'
})
export class QuilleditorService {

  constructor() { 

    var Link = Quill.import('formats/link');
    Link.sanitize = (url) => {
      if(url.indexOf("http") <= -1){
        url = "https://" + url;
      }
      return url;
    }
  }
}
