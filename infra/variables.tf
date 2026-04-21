variable "trusted_admin_cidr" {
  description = "List of approved CIDR ranges for administrative access (SSH and RDP). Supply your organization's trusted admin network or jump-host CIDR(s) as the value."
  type        = list(string)
  # Example: ["10.0.0.0/24", "192.168.1.0/28"]
  # No default is intentionally provided; callers must supply approved CIDRs explicitly.
}

variable "vpc_id" {
  description = "ID of the VPC in which the security group resides."
  type        = string
}
