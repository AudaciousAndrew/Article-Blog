import Cookies from 'js-cookie';

export class cookieFunctions{

  static setCookie(cname, cvalue) {
      Cookies.set(cname, cvalue);
  }

  static getCookie(cname) {
      return Cookies.get(cname);
  }

  static removeCookie(cname){
      Cookies.remove(cname);
  }

}
