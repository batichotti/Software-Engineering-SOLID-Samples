// Code from -> https://github.com/LucasDiogo96/S.O.L.I.D
using System;

namespace SRP.Solution
{
    public class Person
    {
        public int Id { get; set; }
        public string ITIN { get; set; }
        public string Name { get; set; }
        public DateTime BirthDate { get; set; }

        public bool IsValid()
        {
            return !string.IsNullOrWhiteSpace(Name) &&
                   BirthDate != DateTime.MinValue &&
                  !IdentificationService.ValidateITIN(ITIN);
        }
    }
}
