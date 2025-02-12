console.log("contacts.js");
const baseURL = "http://localhost:8080";
const viewContactModal=document.getElementById('view_contact_modal');

// options with default values
const options = {
    placement: 'bottom-right',
    backdrop: 'dynamic',
    backdropClasses:
        'bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40',
    closable: true,
    onHide: () => {
        console.log('modal is hidden');
    },
    onShow: () => {
        console.log('modal is shown');
    },
    onToggle: () => {
        console.log('modal has been toggled');
    },
};

// instance options object
const instanceOptions = {
  id: 'view_contact_modal',
  override: true
};

const contactModal=new Modal(viewContactModal, options, instanceOptions);

function openContactModal(){
    contactModal.show();
}

function closeContactModal(){
    contactModal.hide();
}

async function loadContactData(id) {
  console.log(id);
  try {
    const response = await fetch(`${baseURL}/api/contact/${id}`);
    const data = await response.json();
    console.log(data);

    // Ensure elements exist before updating them
    const nameElement = document.querySelector("#contact_name");
    if (nameElement) nameElement.innerHTML = data.name || "No Name Available";

    const emailElement = document.querySelector("#contact_email");
    if (emailElement) emailElement.innerHTML = data.email || "No Email Provided";

    const imgElement = document.querySelector("#contact_image");
    if (imgElement) imgElement.src = data.picture || "https://isobarscience.com/wp-content/uploads/2020/09/default-profile-picture1.jpg";

    const addressElement = document.querySelector("#contact_address");
    if (addressElement) addressElement.innerHTML = data.address || "No Address Provided";

    // Fixed incorrect ID reference
    const phoneElement = document.querySelector("#contact_number");
    if (phoneElement) phoneElement.innerHTML = data.phoneNumber || "No Phone Number";

    const aboutElement = document.querySelector("#contact_about");
    if (aboutElement) aboutElement.innerHTML = data.description || "No Description Available";

    // Handle Favorite status
    const contactFavorite = document.querySelector("#contact_favorite");

    console.log("Favorite status:", data.favourite); // Debugging

    if (contactFavorite) {
      if (Boolean(data.favourite) === true) {
        contactFavorite.innerHTML = "<i class='fas fa-star text-yellow-400'></i> Favourite Contact";
      } else {
        contactFavorite.innerHTML = "(Not Favorite Contact)";
      }
    }


    // Ensure website & LinkedIn elements exist before updating
    const websiteElement = document.querySelector("#contact_website");
    if (websiteElement) {
      websiteElement.href = data.websiteLink || "#";
      websiteElement.innerHTML = data.websiteLink || "No Website";
    }

    const linkedInElement = document.querySelector("#contact_linkedIn");
    if (linkedInElement) {
      linkedInElement.href = data.linkedinLink || "#";
      linkedInElement.innerHTML = data.linkedinLink || "No LinkedIn";
    }

    // Open the modal
    openContactModal();
  } catch (error) {
    console.log("Error: ", error);
  }
}

//DELETE contact

async function deleteContact(id){
    Swal.fire({
      title: "Are you sure?",
      text: "You won't be able to revert this!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Yes, delete it!"
    }).then((result) => {
      if (result.isConfirmed) {
        const url = `${baseURL}/user/contact/delete/` + id;
        window.location.replace(url);
      }
    });
}



