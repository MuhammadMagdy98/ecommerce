import _ from 'lodash';

export function convertObjectToQueryParams(object: any): string {
  if (!_.isNil(object)) {
    const paramArray: string[] = _.map(_.keys(object), (key) => {
      return encodeURIComponent(key) + '=' + encodeURIComponent(object[key]);
    });
    return '?' + _.join(paramArray, '&');
  } else {
    return '';
  }
}
