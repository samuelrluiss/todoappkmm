//
//  Image.swift
//  iosApp
//
//  Created by Samuel Luis on 15/11/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

extension Image {
    init(resource: KeyPath<SharedResources.images, ImageResource>) {
        self.init(uiImage: SharedResources.images()[keyPath: resource].toUIImage()!)
    }
}
