class AppUtils {

    static BASE_API_URL = 'http://localhost:8081/api';
    static BASE_CUSTOMER_API = this.BASE_API_URL + '/customers';
    static BASE_PRODUCT_API = this.BASE_API_URL + '/products';

    static BASE_CLOUDINARY_URL = 'https://res.cloudinary.com/dev-share/image/upload'
    static BASE_SCALE_700_530_100 = 'c_scale,w_700,h_530,q_100'
    static BASE_SCALE_280_200_85 = 'c_scale,w_280,h_200,q_85'

    static showSuccess = (text) => {
        $.toast({
            heading: 'Success',
            text: text,
            showHideTransition: 'slide',
            icon: 'success',
            position: 'top-right',
        })
    }

    static showError = (text) => {
        $.toast({
            heading: 'Error',
            text: text,
            showHideTransition: 'fade',
            icon: 'error',
            position: 'top-right',
        })
    }
}