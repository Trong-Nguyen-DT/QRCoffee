<?php

/**
 * Plugin Name: payOS
 * Plugin URI: https://casso.vn/plugin-payos-woocommerce/
 * Description:  Quick bank transfer by generating QR codes that are accepted by 37 Vietnam banking App: Vietcombank, Vietinbank, BIDV, ACB, VPBank, MBank, TPBank, Digimi, MSB ... Developed for WooCommerce.
 * Author: Casso Team
 * Author URI: https://casso.vn
 * Text Domain: payos
 * Domain Path: /languages
 * Version: 1.0.0
 * Tested up to: 6.0
 * License: GNU General Public License v3.0
 */


defined('ABSPATH') or exit;


// Make sure WooCommerce is active
if (!in_array('woocommerce/woocommerce.php', apply_filters('active_plugins', get_option('active_plugins')))) {
    return;
}

//load plugin code
add_action('plugins_loaded', 'payos_gateway_init', 11);

function payos_gateway_init()
{
    require_once(plugin_basename('classes/class-wc-gateway-payos.php'));
}

//register payos gateway 
add_filter('woocommerce_payment_gateways', 'payos_add_gateways');
add_action('plugins_loaded', 'payos_load_plugin_textdomain');
// add_filter( 'auto_update_plugin', '__return_true' );
function payos_add_gateways($gateways)
{
    $gateways[] = 'WC_Gateway_payOS';
    return $gateways;
}
add_action( 'init', 'payos_add_settting');

function payos_add_settting(){
    if ( class_exists( 'WooCommerce' ) ) {
        // Add "Settings" link when the plugin is active
        add_filter( 'plugin_action_links_' . plugin_basename( __FILE__ ),'payos_add_settings_link');
    }
}
function payos_add_settings_link( $links ) {
    $settings = array( '<a href="' . admin_url( 'admin.php?page=wc-settings&tab=checkout&section=payos' ) . '">' . __( 'Thiết lập', 'woocommerce' ) . '</a>' );
    $links    = array_reverse( array_merge( $links, $settings ) );

    return $links;
}
function payos_load_plugin_textdomain()
{
    load_plugin_textdomain('payos', false, dirname( plugin_basename( __FILE__ ) ) . '/languages' ); 

}