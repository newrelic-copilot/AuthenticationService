variable "vpc_id" {
  description = "The VPC ID where the security group is deployed"
  type        = string
}

variable "admin_cidr" {
  description = "Approved CIDR range for SSH (port 22) administrative access. Must not be 0.0.0.0/0 or ::/0."
  type        = string

  validation {
    condition     = can(cidrhost(var.admin_cidr, 0)) && !contains(["0.0.0.0/0", "::/0"], var.admin_cidr)
    error_message = "admin_cidr must be a valid CIDR notation and must not be an unrestricted range (0.0.0.0/0 or ::/0). Provide a specific approved network range."
  }
}

variable "management_cidr" {
  description = "Approved CIDR range for RDP (port 3389) management access. Must not be 0.0.0.0/0 or ::/0."
  type        = string

  validation {
    condition     = can(cidrhost(var.management_cidr, 0)) && !contains(["0.0.0.0/0", "::/0"], var.management_cidr)
    error_message = "management_cidr must be a valid CIDR notation and must not be an unrestricted range (0.0.0.0/0 or ::/0). Provide a specific approved network range."
  }
}
