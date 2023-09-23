document.querySelectorAll('.sidebar-submenu').forEach(e => {
    e.querySelector('.sidebar-menu-dropdown').onclick = (event) => {
        event.preventDefault()
        e.querySelector('.sidebar-menu-dropdown .dropdown-icon').classList.toggle('active')

        let dropdown_content = e.querySelector('.sidebar-menu-dropdown-content')
        let dropdown_content_lis = dropdown_content.querySelectorAll('li')

        let active_height = dropdown_content_lis[0].clientHeight * dropdown_content_lis.length

        dropdown_content.classList.toggle('active')

         dropdown_content.style.height = dropdown_content.classList.contains('active') ? active_height + 'px' : '0'
    }
})

let category_options = {
    // series: [70, 75, 41, 27],
    // labels: ['Coffee', 'Trà Sữa', 'Trà Chanh', 'Kem'],

    chart: {
        type: 'donut',
    },
    colors: ['#6ab04c', '#2980b9', '#f39c12', '#d35400']
}

// Gọi API lấy dữ liệu danh mục
fetch('/admin/category-data')
    .then(response => response.json())
    .then(data => {
        // Chuyển dữ liệu từ API thành dạng thích hợp cho biểu đồ
        const seriesData = data.map(item => item.quantity);
        const labels = data.map(item => item.categoryName);

        // Cập nhật category_options

         category_options.series = seriesData;
         category_options.labels = labels;



        // Tạo hoặc cập nhật biểu đồ danh mục ở đây
        let category_chart = new ApexCharts(document.querySelector("#category-chart"), category_options);
        category_chart.render();
    })
    .catch(error => console.error(error));




let customer_options = {
    series: [{
        // name: "Store Customers",
        data: []
    }],
    colors: ['#6ab04c', '#2980b9'],
    chart: {
        height: 350,
        type: 'line',
    },
    dataLabels: {
        enabled: false
    },
    stroke: {
        curve: 'smooth'
    },
    xaxis: {
        // categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep'],
        categories: [],
    },
    legend: {
        position: 'top'
    }
}


fetch('/admin/amount-data')
    .then(response => response.json())
    .then(data => {
        // Chuyển dữ liệu từ API thành dạng thích hợp cho biểu đồ
        // const seriesData = data.map(item => item.quantity);
        const seriesData = data.map(item => item.amount);
        const labels = data.map(item => item.month);


        // Cập nhật category_options


        customer_options.series.data = seriesData
        customer_options.xaxis.categories = labels

        let customer_chart = new ApexCharts(document.querySelector("#customer-chart"), customer_options)
        customer_chart.render()
    })
    .catch(error => console.error(error));

// let customer_chart = new ApexCharts(document.querySelector("#customer-chart"), customer_options)
// customer_chart.render()

setDarkChart = (dark) => {
    let theme = {
        theme: {
            mode: dark ? 'dark' : 'light'
        }
    }

    customer_chart.updateOptions(theme)
    category_chart.updateOptions(theme)
} 

// DARK MODE TOGGLE
let darkmode_toggle = document.querySelector('#darkmode-toggle')

darkmode_toggle.onclick = (e) => {
    e.preventDefault()
    document.querySelector('body').classList.toggle('dark')
    darkmode_toggle.querySelector('.darkmode-switch').classList.toggle('active')
    setDarkChart(document.querySelector('body').classList.contains('dark'))
}

let overlay = document.querySelector('.overlay')
let sidebar = document.querySelector('.sidebar')

document.querySelector('#mobile-toggle').onclick = () => {
    sidebar.classList.toggle('active')
    overlay.classList.toggle('active')
}

document.querySelector('#sidebar-close').onclick = () => {
    sidebar.classList.toggle('active')
    overlay.classList.toggle('active')
}